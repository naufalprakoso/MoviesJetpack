package com.naufalprakoso.moviesjetpack.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {
    companion object {
        private const val RESOURCE = "GLOBAL"
        private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment(){
            espressoTestIdlingResource.increment()
        }

        fun decrement(){
            espressoTestIdlingResource.decrement()
        }

        fun getEspressoIdlingResource(): IdlingResource {
            return espressoTestIdlingResource
        }
    }
}