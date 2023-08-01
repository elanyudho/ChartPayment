package com.example.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest =
            chain.request().newBuilder().build()

        return chain.proceed(newRequest)
    }
}