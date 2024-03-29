package com.DevAsh.RecWalletBackend.Api

import com.DevAsh.RecWalletBackend.Api.Request.OtpDetails
import com.DevAsh.RecWalletBackend.Service.Otp.Manager.OtpTypes
import com.DevAsh.RecWalletBackend.Service.Otp.OtpService
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


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

    @PostMapping("/pay/verifyOtp")
    fun verifyPayOtp(
            @RequestBody otpDetails: OtpDetails,
            httpServletResponse: HttpServletResponse
    ) {

        println(otpDetails)

        val number = otpDetails.number
        val otp = otpDetails.otp

        if (otpService!!.verifyOtp("rPay@${number}",number,otp,OtpTypes.rPayVerification)){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        }else{
            httpServletResponse.status=HttpServletResponse.SC_FORBIDDEN
        }
    }

    @PostMapping("/business/verifyOtp")
    fun verifyBusinessOtp(
            @RequestBody otpDetails:OtpDetails,
            httpServletResponse: HttpServletResponse
    ) {
        println(otpDetails)
        val number = otpDetails.number
        val otp = otpDetails.otp
        if (otpService!!.verifyOtp("rBusiness@${number}",number,otp,OtpTypes.rPayVerification)){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        }else{
            httpServletResponse.status=HttpServletResponse.SC_FORBIDDEN
        }
    }
}


