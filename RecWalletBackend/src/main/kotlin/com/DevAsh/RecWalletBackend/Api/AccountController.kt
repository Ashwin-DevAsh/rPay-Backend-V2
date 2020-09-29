package com.DevAsh.RecWalletBackend.Api

import com.DevAsh.RecWalletBackend.Api.Request.AccountDetailsRequest
import com.DevAsh.RecWalletBackend.Api.Response.AccountDetailsResponse
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Service.Account.AccountService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/v1/account")
class AccountController(private final var accountService: AccountService){

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @GetMapping("/pay/isAccountExist/{number}")
    fun isAccountExist(@PathVariable number: String, httpServletResponse: HttpServletResponse):AccountDetailsResponse?{
        val payAccount = accountService.isPayAccountExist("rPay@$number")
        if(payAccount==null){
            httpServletResponse.status=HttpServletResponse.SC_BAD_REQUEST
            return null
        }
        val jwtToken = getJwtToken(payAccount.id!!)
        httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        return AccountDetailsResponse.fromPayAccount(payAccount, jwtToken)
    }

    @PostMapping("/pay/addAccount")
    fun addPayAccount(
            @RequestBody accountDetailsRequest: AccountDetailsRequest,
            httpServletResponse: HttpServletResponse
    ):AccountDetailsResponse?{
        val payAccount = accountDetailsRequest.toPayAccount()
        return if(accountService.addPayAccount(payAccount)){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
            val id = "rPay@${accountDetailsRequest.phoneNumber}"
            val jwtToken = getJwtToken(id)
            AccountDetailsResponse.fromAccountDetails(accountDetailsRequest,jwtToken,id)
        }else{
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
            null
        }
    }

    @GetMapping("/protected/pay/getMyAccount/{id}")
    fun getMyAccount( @PathVariable id: String,httpServletResponse: HttpServletResponse ):PayAccount?{
        val payAccount = accountService.getMyAccount(id)
        if(payAccount==null){
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
        }
        return payAccount
    }

    @GetMapping("/protected/getPayAccounts")
    fun getPayAccounts():List<PayAccount>{
        return accountService.getPayAccounts()
    }


    fun getJwtToken(id:String):String{
       return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256,secretKey.toByteArray())
                .setClaims(mapOf("id" to id))
                .compact()
    }

    @GetMapping("/protected/pay/GetMyAccount")
    fun getMyAccount(response: HttpServletResponse,request: HttpServletRequest):AccountDetailsResponse{
        val header = request.getHeader("token")
        return AccountDetailsResponse.fromPayAccount(accountService.getMyAccount(getId(header))!!,header)
    }

    fun getId(header: String):String{
        return Jwts.parser().setSigningKey(secretKey.toByteArray()).parseClaimsJws(header).body["id"] as String;
    }

}

