package me.alhaz.snippet.movieapp.helper

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOThreadExecutor: Executor {

    private var mDiskIO: Executor

    init {
        mDiskIO = Executors.newSingleThreadExecutor()
    }

    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }

}