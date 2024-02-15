package com.example.data.network

import android.util.Log
import com.example.data.common.PREF_TOKEN
import com.example.data.utils.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val localStorage: LocalStorage) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token: String? = localStorage[PREF_TOKEN]
        Log.d("HEADER", "token: Bearer $token")
        request = request.newBuilder()
            .addHeader("Authorization", token ?: "")
            .build()
        return chain.proceed(request)
    }
}