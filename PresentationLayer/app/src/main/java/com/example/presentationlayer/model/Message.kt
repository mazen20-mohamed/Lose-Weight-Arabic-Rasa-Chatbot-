package com.example.presentationlayer.model

import com.example.presentationlayer.Constants

data class Message(
    var message: String?= null,
    var id: Int = Constants.USER,
    var imageUrl: String?=null
)