package com.DevAsh.RecWalletBackend.Api

import com.DevAsh.RecWalletBackend.Service.Otp.Manager.OtpTypes
import com.DevAsh.RecWalletBackend.Service.Otp.OtpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.websocket.server.PathParam
import kotlin.random.Random

@RestController
@RequestMapping("/api/v1/otp")
class OtpController{

    @Autowired
    final var otpService:OtpService?=null

    @GetMapping("/pay/getOtp/{number}")
    fun getPayOtp(@PathVariable number: String,httpServletResponse: HttpServletResponse){

        val isOtpSend:Boolean? = otpService?.sendOtp(
                "rPay@$number",
                number,
                OtpTypes.rPayVerification,
                "1"
        )

        if(isOtpSend!=null && isOtpSend){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        }else{
            httpServletResponse.status=HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }

    @GetMapping("/business/getOtp/{number}")
    fun getBusinessOtp(@PathVariable number: String,httpServletResponse: HttpServletResponse){

        val isOtpSend:Boolean? = otpService?.sendOtp(
                "rBusiness@$number",
                number,
                OtpTypes.rBusinessVerification,
                "1"
        )

        if(isOtpSend!=null && isOtpSend){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        }else{
            httpServletResponse.status=HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        }
    }

    @PostMapping("/pay/verifyOtp/")
    fun verifyPayOtp(
            @RequestBody otpObject:OtpObject,
            httpServletResponse: HttpServletResponse
    ) {

        val number = otpObject.number
        val otp = otpObject.otp

        if (otpService!!.verifyOtp("rPay@${number}",number,otp,OtpTypes.rPayVerification)){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        }else{
            httpServletResponse.status=HttpServletResponse.SC_FORBIDDEN
        }
    }


}
class OtpObject(val number: String,val otp: String)


