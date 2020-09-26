package com.DevAsh.RecWalletBackend

import com.DevAsh.RecWalletBackend.Api.Response.AccountDetailsResponse
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.FromType
import com.DevAsh.RecWalletBackend.Service.Otp.OtpService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.persistence.EntityManagerFactory

@SpringBootApplication
class RecWalletBackendApplication{
	companion object{
		var entityManagerFactory :EntityManagerFactory?=null
	}
}



fun main(args: Array<String>) {
	val context = runApplication<RecWalletBackendApplication>(*args)
	RecWalletBackendApplication.entityManagerFactory = context.getBean(EntityManagerFactory::class.java)
	AccountDetailsResponse.fromPayAccount(PayAccount(),"")
}
