package me.alhaz.snippet.movieapp.repositories

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import me.alhaz.snippet.movieapp.helper.AppExecutors
import me.alhaz.snippet.movieapp.valueobject.ApiResponse
import me.alhaz.snippet.movieapp.valueobject.Resource
import me.alhaz.snippet.movieapp.valueobject.Status

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutor: AppExecutors){

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            }
            else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {

        val apiResponse: LiveData<ApiResponse<RequestType>> = createCall()

        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->

            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response.status) {

                Status.SUCCESS -> {
                    appExecutor.diskIO().execute {
                        saveCallResult(response.body)
                        appExecutor.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }

                Status.EMPTY -> {
                    appExecutor.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }

                Status.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.message, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

//    @WorkerThread
//    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

}