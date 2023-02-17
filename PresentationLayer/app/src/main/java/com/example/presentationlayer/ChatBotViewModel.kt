package com.example.presentationlayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import com.example.presentationlayer.model.UserMessage

class ChatBotViewModel: ViewModel() {


    private val mChatBotRepository: ChatBotRepository = ChatBotRepository

    fun getBotMessages(): LiveData<List<BotMessage>> = mChatBotRepository.getBotMessages()

    fun getConversation(): LiveData<MutableList<Message>> = mChatBotRepository.getConversation()

    fun addUserMessageInConversation(message: String) {
        mChatBotRepository.addUserMessageInConversation(
            UserMessage(
                message = message,
                id = Constants.USER
            )
        )
    }

    fun queryBot(message: String) {
        mChatBotRepository.queryBot(message)
    }

}