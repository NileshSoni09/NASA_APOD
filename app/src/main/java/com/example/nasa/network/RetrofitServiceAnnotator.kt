package com.example.nasa.network

import com.example.nasa.model.ApodDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceAnnotator {

    @GET("apod")
    fun getAPOD(
        @Query("date") Date: String?,
        @Query("hd") hd: Boolean,
        @Query("api_key") API_KEY: String?
    ): Call<ApodDataModel>
}