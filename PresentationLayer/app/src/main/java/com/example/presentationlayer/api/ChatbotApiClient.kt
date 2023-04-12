package com.example.presentationlayer.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentationlayer.Constants
import com.example.presentationlayer.model.BotMessage
import com.example.presentationlayer.model.Message
import com.example.presentationlayer.model.UserMessage
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object AppExecutors {

    private val mNetworkIO: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService = mNetworkIO
}
object ChatBotApiClient {

    private val mBotResponse: MutableLiveData<List<BotMessage>> = MutableLiveData()
    private val mConversation: MutableLiveData<MutableList<Message>> = MutableLiveData()
    private var mBotQueryRunnable: BotQueryRunnable?= null


    fun getBotMessages(): LiveData<List<BotMessage>>  = mBotResponse

    fun getConversation(): LiveData<MutableList<Message>> = mConversation

    fun addUserMessageInConversation(userMessage: UserMessage) {
        var oldConversation: MutableList<Message>? = mConversation.value

        if(oldConversation==null) {
            var newConversation: MutableList<Message> = mutableListOf()
            newConversation.add(Message(message = userMessage.message, id = Constants.USER))
            newConversation.add(Message(message = null, id=Constants.LOADING))
            mConversation.postValue(newConversation)
        } else {
            oldConversation.add(Message(message = userMessage.message, id = Constants.USER))
            oldConversation.add(Message(message = null, id=Constants.LOADING))
            mConversation.postValue(oldConversation)
        }
    }

    private fun addBotMessageInConversation(botMessage: Message) {
        var oldConversation: MutableList<Message>? = mConversation.value

        if(oldConversation==null) {
            var newConversation: MutableList<Message> = mutableListOf()
            newConversation.add(botMessage)
            mConversation.postValue(newConversation)
        } else {
            if(oldConversation[oldConversation.size-1].id==Constants.LOADING)
                oldConversation.removeAt(oldConversation.size-1)

            oldConversation.add(botMessage)
            mConversation.postValue(oldConversation)
        }
    }

    private fun isBotLoading(): Boolean{

        val currentConversation = mConversation.value ?: return false

        return (currentConversation.isNotEmpty() && currentConversation[currentConversation.size-1].id==Constants.LOADING)
    }

    fun queryBot(sender: Int, message: String) {

        if(mBotQueryRunnable!=null) mBotQueryRunnable = null

        mBotQueryRunnable = BotQueryRunnable(sender, message)

        val handler: Future<*> = AppExecutors.networkIO().submit(mBotQueryRunnable)

        AppExecutors.networkIO().schedule(Runnable {

            // stop request - timeout occurred
            handler.cancel(true)

            if(isBotLoading()) {
                addBotMessageInConversation(
                    Message(
                        message = "Bot is taking too long to process your request. Please try again after some time",
                        id = Constants.BOT
                    )
                )
            }

        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)

    }



    private class BotQueryRunnable(
        private var sender: Int,
        private var message: String,
        var cancelRequest: Boolean = false
    ): Runnable {


        override fun run() {

            try {

                val response:Response<*> = getMessageBot(sender, message).execute()

                if(cancelRequest) return
                Log.d("##",response.body().toString()+ " "+ response.isSuccessful + " " + sender)
                if(response.code()==200) {
                    val botResponseList: List<BotMessage> = (response.body() as List<BotMessage>)
                    Log.d("##",botResponseList.size.toString())
                    botResponseList?.let {
                        mBotResponse.postValue(botResponseList)

                        var prevConversations: List<Message>? = mConversation.value

                        var botMessages: MutableList<Message> = mutableListOf()

                        for(response in botResponseList) {
                            botMessages.add(
                                Message(
                                    message = response.response,
                                    id = Constants.BOT,
                                    imageUrl = response.imageUrl
                                ))
                        }

                        if(prevConversations==null) {
                            mConversation.postValue(botMessages)

                        } else {

                            var oldConversation: MutableList<Message>? = mConversation.value
                            oldConversation?.let { list ->

                                // hide loading first
                                if(list[list.size-1].id==Constants.LOADING) list.removeAt(list.size-1)

                                list.addAll(botMessages)
                                mConversation.postValue(list)
                            }
                        }
                    }

                } else {
                    addBotMessageInConversation(
                        Message(
                            message = "Wrong response. \n\nCode: ${response.code()}\nMessage: ${response.message()}",
                            id = Constants.BOT
                        )
                    )
                }


            } catch (e: Exception) {
                e.printStackTrace()
                addBotMessageInConversation(
                    Message(
                        message = "Exception Occurred. \n\nMessage: ${e.message}",
                        id = Constants.BOT
                    )
                )
            }

        }
        private fun getMessageBot(sender: Int, message: String): Call<List<BotMessage>> = ServiceGenerator.CHAT_BOT_API.messageBot(
            userMessage = Message(
                message= message,
                id = sender
            )
        )
        // cancel bot request - may be on back press
        fun cancelRequest() {
            cancelRequest = true
        }
    }
}
