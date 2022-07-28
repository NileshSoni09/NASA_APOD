package com.example.nasa.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasa.Constants
import com.example.nasa.model.ApodDataModel
import com.example.nasa.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {

    var apodDataModelUpdate = MutableLiveData<ApodDataModel>()

    fun getAPODData(date: String) {

        val call: Call<ApodDataModel>? = RetrofitClient.getInstance()?.getServiceAnnotator()
            ?.getAPOD(date, false, Constants.API_KEY)


        call?.enqueue(object : Callback<ApodDataModel> {
            override fun onResponse(call: Call<ApodDataModel>, response: Response<ApodDataModel>) {
                val apod: ApodDataModel? = response.body()
                Log.d("", "Response body: " + response.body())
                apodDataModelUpdate.value = response.body()
            }

            override fun onFailure(call: Call<ApodDataModel>, t: Throwable) {

            }

        })

    }

}