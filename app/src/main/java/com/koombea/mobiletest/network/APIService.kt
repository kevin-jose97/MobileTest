package com.koombea.mobiletest.network

import com.koombea.mobiletest.model.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    fun getUsers(@Url url: String): Call<List<UserModel>?>?
}