package com.compasso.bookschange.model.main.home.bookApi

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().build())
    }
}
