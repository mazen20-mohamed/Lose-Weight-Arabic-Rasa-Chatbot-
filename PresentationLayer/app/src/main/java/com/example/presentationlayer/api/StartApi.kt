package com.example.presentationlayer.api

import com.example.presentationlayer.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP


class StartApi {

    private val retrofit: ApiService = Retrofit.Builder()
    .baseUrl("https://744c-197-56-21-219.eu.ngrok.io/demo/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)

    fun getInstance(): ApiService {
        return retrofit
    }
}

object ServiceGenerator {

    val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val CHAT_BOT_API: BotApi by lazy {
        retrofitBuilder.build()
            .create(BotApi::class.java)
    }

}