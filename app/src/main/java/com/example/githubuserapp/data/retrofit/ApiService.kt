package com.example.githubuserapp.data.retrofit

import com.example.githubuserapp.data.response.DetailUserResponse
import com.example.githubuserapp.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun getUsername(
       @Query("q") q : String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>
}