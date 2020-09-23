package com.DevAsh.RecWalletBackend.Service.Otp.Manager

import ClickSend.Api.SmsApi
import ClickSend.ApiClient
import ClickSend.Model.SmsMessage
import ClickSend.Model.SmsMessageCollection
import com.DevAsh.RecWalletBackend.Service.Otp.Manager.OtpManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.Exception


@Service("clickSend")
class ClickSend() : OtpManager {

    @Value("\${clickSend.userName}")
    final var userName:String?=null
    @Value("\${clickSend.apiKey}")
    final var apiKey:String?=null


    override fun sendOtp(number: Number, otp: Number, appId: String):Boolean {
        val apiClient = ApiClient()
        apiClient.setUsername(userName)
        apiClient.setPassword(apiKey)

        val apiInstance = SmsApi(apiClient)
        val smsMessage = SmsMessage()


        smsMessage.body("$otp")
        smsMessage.to("+$number")

        val smsMessageList: List<SmsMessage> = listOf(smsMessage)
        val smsMessages = SmsMessageCollection()
        smsMessages.messages(smsMessageList)

        return try {
            val result = apiInstance.smsSendPost(smsMessages)
            println(result)
            true
        }catch (e:Exception){
            println(e)
            false
        }
    }


    override fun sendEmailOtp(otp: Number, appId: String):Boolean {
        return false
    }



}