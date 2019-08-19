package com.naufalprakoso.moviesjetpack.ui.utils

import com.naufalprakoso.moviesjetpack.utils.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object{
        private val instant = Executor { it.run() }
    }
}