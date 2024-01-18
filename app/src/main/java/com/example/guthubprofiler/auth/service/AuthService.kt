package com.example.guthubprofiler.auth.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {
    @GET("/user")
    fun fetchUser(
        @Header("Authorization") personalToken: String
    ): Call<AuthUserServiceModel>
}