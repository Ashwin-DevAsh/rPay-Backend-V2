package com.DevAsh.RecWalletBackend

import com.DevAsh.RecWalletBackend.Database.Transactions.FromType
import com.DevAsh.RecWalletBackend.Service.Otp.OtpService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import javax.persistence.EntityManagerFactory

@SpringBootApplication
class RecWalletBackendApplication{}

fun main(args: Array<String>) {
	val context = runApplication<RecWalletBackendApplication>(*args)

	val entityManagerFactory = 	context.getBean(EntityManagerFactory::class.java)
    val entityManager = entityManagerFactory.createEntityManager()

	val otpService = context.getBean(OtpService::class.java)


//	entityManager.transaction.begin()
//	val payAccount = PayAccount(
//			id="rpay@919551574355",
//			userName= "Ashwin",
//			phoneNumber = "919551574355",
//			email="2017ashwin@gmail.com",
//			fcmToken="123"
//	)
//	entityManager.persist(payAccount)
//	val fromType=FromType()
//	fromType.payAccount = payAccount;
//	entityManager.persist(fromType)
//	entityManager.transaction.commit()


	val fromType:List<FromType>? =
			entityManager
					.createQuery(
							"select f from FromType f where f.payAccount.id='rpay@919551574355'",
							FromType::class.java)
					.resultList
	println("Result = $fromType")



}
