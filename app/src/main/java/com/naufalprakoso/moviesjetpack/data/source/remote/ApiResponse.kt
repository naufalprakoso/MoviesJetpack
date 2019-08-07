package com.naufalprakoso.moviesjetpack.data.source.remote

import androidx.annotation.NonNull

class ApiResponse<T>(
    private val status: StatusResponse? = null,
    private val message: String? = "",
    private val body: T? = null
) {
    companion object {
//        fun <T> success(body: T?): ApiResponse<T> {
//            return ApiResponse(StatusResponse.SUCCESS, body.toString(), null)
//        }
//
//        fun <T> empty(msg: String, body: T?): ApiResponse<T> {
//            return ApiResponse(StatusResponse.SUCCESS, body, msg)
//        }
//
//        fun <T> error(msg: String, body: T?): ApiResponse<T> {
//            return ApiResponse(StatusResponse.SUCCESS, body, msg)
//        }
    }
}