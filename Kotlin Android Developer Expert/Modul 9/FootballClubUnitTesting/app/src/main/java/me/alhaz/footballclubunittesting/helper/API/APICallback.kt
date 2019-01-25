package me.alhaz.footballclubunittesting.helper.API

interface APICallback<T> {

    fun onDataLoaded(data: T)
    fun onDataError(message: String?)
}