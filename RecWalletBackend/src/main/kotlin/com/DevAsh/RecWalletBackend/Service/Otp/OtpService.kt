package com.DevAsh.RecWalletBackend.Service.Otp

import com.DevAsh.RecWalletBackend.Dao.OtpDao
import com.DevAsh.RecWalletBackend.Service.Otp.Manager.OtpManager
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import javax.persistence.Id
import kotlin.random.Random

@Service
class OtpService(
        @Qualifier("clickSend") final val otpManager: OtpManager,
        @Qualifier("OtpDatabase") final val otpDao: OtpDao
) {


    fun sendOtp(id: String,number:String,type:String,appId:String):Boolean?{
        val otp = Random.nextInt(1000,9999)
        println(otp)
        if(!otpDao.insertOtp(
                id=id,
                number = number,
                otp = otp.toString(),
                type = type
        )){
           return false
        }
//        val isOtpSend = otpManager.sendOtp(number,otp,appId)
        return true
    }

    fun verifyOtp(id:String,number: String,otp: String,type: String):Boolean{
        return otpDao.verifyOtp(id,otp,type)
    }

}