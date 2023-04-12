package com.example.presentationlayer.model
import com.example.presentationlayer.Constants
import com.example.presentationlayer.userId


data class UserMessage(
    var message: String?= null,
    var id: Int = userId
)
