package com.example.githubuserapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.data.response.GithubResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import kotlinx.coroutines.Dispatchers
class MainViewModel : ViewModel() {

    private val _userList = MutableLiveData<List<ItemsItem>>()
    val userList: LiveData<List<ItemsItem>> = _userList

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    companion object{
        private const val TAG ="MainViewModel"
        private const val USER_ID = "anggra"
    }

    fun setUsername(query:  String){
        findGithubResponse(query)
    }

    init {
        findGithubResponse(USER_ID)
    }

    private fun findGithubResponse(query: String) {

        _isloading.value = true
        val client = ApiConfig.getApiService().getUsername(query)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isloading.value = false
                if (response.isSuccessful){
                    _userList.value = response.body()?.items
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isloading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}