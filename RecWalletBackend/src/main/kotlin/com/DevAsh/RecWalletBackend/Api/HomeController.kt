package com.DevAsh.RecWalletBackend.Api

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestController
@RequestMapping("/")
class HomeController {

    @RequestMapping
    fun welcome():String{
        return "Welcome to core part of RecWallet"
    }

}