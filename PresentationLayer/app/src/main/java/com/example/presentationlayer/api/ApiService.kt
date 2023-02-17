package com.example.presentationlayer.api

import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("login")
    fun login(@Query("email")email:String,@Query("password")password:String): Call<Long?>?

    @GET("register")
    fun register(@Query("userName")userName:String,@Query("email")email:String,@Query("password")password:String,@Query("gender")gender:String,@Query("height")height:Int,@Query("weight")weight:Double,@Query("age")age:Int,@Query("activationRate")activationRate:Double): Call<ResponseBody>

}
