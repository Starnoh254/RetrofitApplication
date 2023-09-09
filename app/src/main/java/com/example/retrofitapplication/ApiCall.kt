package com.example.retrofitapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiCall {
    @POST("member_signin")
    suspend fun postData(@Body requestBody: MyBody): Response<MyData>
}