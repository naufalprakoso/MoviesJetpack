package com.naufalprakoso.moviesjetpack.utils

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelUtil {
    companion object {
        fun <T : ViewModel> createFor(model: T): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @NonNull
                override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(model::class.java)) {
                        return model as T
                    }
                    throw IllegalArgumentException("unexpected model class \$modelClass")
                }
            }
        }
    }
}