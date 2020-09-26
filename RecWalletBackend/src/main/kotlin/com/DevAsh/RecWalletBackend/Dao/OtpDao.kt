package com.DevAsh.RecWalletBackend.Dao

import com.DevAsh.RecWalletBackend.Database.Otp


interface OtpDao {
    fun insertOtp( id:String,number: String,otp:String,type:String ):Boolean
    fun verifyOtp(id:String,otp:String,type:String):Boolean
    fun deleteOtp(id:String):Boolean
    fun isVerified(id: String):Boolean
}