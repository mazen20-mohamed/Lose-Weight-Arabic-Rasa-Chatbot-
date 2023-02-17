package com.example.presentationlayer.model
import com.example.presentationlayer.Constants


data class UserMessage(
    var message: String?= null,
    var id: Int = Constants.USER
)
