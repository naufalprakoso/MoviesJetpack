package com.naufalprakoso.moviesjetpack.utils

import androidx.test.espresso.IdlingResource

class FetchingIdlingResource: IdlingResource, FetcherListener {
    private var idle = true
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = FetchingIdlingResource::class.java.simpleName

    override fun isIdleNow(): Boolean = idle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        resourceCallback = callback
    }

    override fun doneFetching() {
        idle = true
        resourceCallback?.onTransitionToIdle()
    }

    override fun beginFetching() {
        idle = false
    }
}