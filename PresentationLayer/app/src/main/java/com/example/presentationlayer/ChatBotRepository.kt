package com.example.presentationlayer

import androidx.lifecycle.LiveData
import com.example.presentationlayer.api.ChatBotApiClient
import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import com.example.presentationlayer.model.UserMessage

object ChatBotRepository {

    private val mChatBotApiClient: ChatBotApiClient = ChatBotApiClient

    fun getBotMessages(): LiveData<List<BotMessage>> = mChatBotApiClient.getBotMessages()

    fun getConversation(): LiveData<MutableList<Message>> = mChatBotApiClient.getConversation()

    fun addUserMessageInConversation(userMessage: UserMessage) {
        mChatBotApiClient.addUserMessageInConversation(userMessage)
    }


    fun queryBot(message: String) {
        mChatBotApiClient.queryBot(Constants.USER, message)
    }
}