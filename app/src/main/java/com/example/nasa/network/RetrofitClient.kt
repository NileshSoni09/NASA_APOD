package com.example.nasa.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    private var serviceAnnotator: RetrofitServiceAnnotator? = null

    private constructor() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        serviceAnnotator = retrofitBuilder.create(RetrofitServiceAnnotator::class.java)
    }

    companion object {
        private var instance: RetrofitClient? = null
        fun getInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
            }
            return instance
        }
    }

    fun getServiceAnnotator(): RetrofitServiceAnnotator? {
        return serviceAnnotator
    }
}