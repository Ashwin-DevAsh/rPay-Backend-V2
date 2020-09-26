package com.DevAsh.RecWalletBackend.Api

import com.DevAsh.RecWalletBackend.Api.Request.AccountDetails
import com.DevAsh.RecWalletBackend.Api.Response.AccountDetailsResponse
import com.DevAsh.RecWalletBackend.Service.Account.AccountService
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/v1/account")
class AccountController(private final var accountService: AccountService){

    @GetMapping("/pay/isAccountExist/{number}")
    fun isAccountExist(@PathVariable number: String, httpServletResponse: HttpServletResponse):AccountDetailsResponse?{
        val payAccount = accountService.isPayAccountExist("rPay@$number")
        val jwtToken = ""
        if(payAccount==null){
            httpServletResponse.status=HttpServletResponse.SC_BAD_REQUEST
            return null
        }
        httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
        return AccountDetailsResponse.fromPayAccount(payAccount,jwtToken)
    }

    @PostMapping("/pay/addAccount")
    fun addPayAccount(@RequestBody accountDetails: AccountDetails,httpServletResponse: HttpServletResponse):AccountDetailsResponse?{

        println(accountDetails)

        return if(accountService.addPayAccount(accountDetails.toPayAccount())){
            httpServletResponse.status=HttpServletResponse.SC_ACCEPTED
            val jwtToken = ""
            AccountDetailsResponse.fromAccountDetails(accountDetails,jwtToken)
        }else{
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
            null
        }
    }

}

