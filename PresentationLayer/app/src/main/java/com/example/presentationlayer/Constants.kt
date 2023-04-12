package com.example.presentationlayer

class Constants {

    companion object {
        val NGROCK_URL = "https://a104-154-179-187-135.eu.ngrok.io"
        val BASE_URL = "$NGROCK_URL/webhooks/rest/"
        val NETWORK_TIMEOUT = 5000L
        val MESSAGE_TEXT_NULL = "NA"

        var USER = 0
        val BOT = 1
        val LOADING = 2
    }
}