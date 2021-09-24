package com.koombea.mobiletest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koombea.mobiletest.model.UserModel
import com.koombea.mobiletest.network.APIService
import com.koombea.mobiletest.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserListViewModel : ViewModel(){

    private var usersList: MutableLiveData<List<UserModel>>? = null


    fun UserListViewModel() {
        usersList = MutableLiveData<List<UserModel>>()
    }

    fun getUsersListObserver(): MutableLiveData<List<UserModel>>? {
        return usersList
    }

    fun makeApiCall() {
        val apiService: APIService = RetrofitInstance.getRetrofitClient()!!.create(APIService::class.java)
        val call: Call<List<UserModel>?>? = apiService.getUsers("")
        call?.enqueue(object : Callback<List<UserModel>?> {

            override fun onResponse(
                call: Call<List<UserModel>?>,
                response: Response<List<UserModel>?>
            ) {
                usersList?.postValue(response.body())
            }

            override fun onFailure(call: Call<List<UserModel>?>, t: Throwable) {
                usersList?.postValue(null)
            }
        })


    }

}


