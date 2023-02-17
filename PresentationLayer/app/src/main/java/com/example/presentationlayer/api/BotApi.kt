package com.example.presentationlayer.api


import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface BotApi {
    @POST("webhook")
    fun messageBot(@Body userMessage: Message): Call<List<BotMessage>>
}
