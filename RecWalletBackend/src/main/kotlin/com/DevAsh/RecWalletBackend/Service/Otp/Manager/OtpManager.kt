package com.DevAsh.RecWalletBackend.Service.Otp.Manager


interface OtpManager {
    fun sendOtp(number: Number, otp: Number, appId:String):Boolean
    fun sendEmailOtp(otp: Number,appId: String):Boolean
}