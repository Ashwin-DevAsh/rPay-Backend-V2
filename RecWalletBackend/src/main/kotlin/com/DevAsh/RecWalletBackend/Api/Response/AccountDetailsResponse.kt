package com.DevAsh.RecWalletBackend.Api.Response

import com.DevAsh.RecWalletBackend.Api.Request.AccountDetails
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.Transaction
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.BankAccount
import com.DevAsh.RecWalletBackend.RecWalletBackendApplication


class AccountDetailsResponse (
        name: String?,
        phoneNumber: String?,
        email: String?,
        password: String?,
        fcmToken: String?,
        qr: String?,
        storeName: String?,
        var jwtToken: String?,
        var bankAccount: List<BankAccount>,
        var transaction: List<Transaction>
) : AccountDetails(
        name,
        phoneNumber,
        email,
        password,
        fcmToken,
        qr,
        storeName
) {

    companion object{


        fun fromAccountDetails(accountDetails: AccountDetails,jwtToken: String?):AccountDetailsResponse{
            return AccountDetailsResponse(
                    name = accountDetails.name,
                    phoneNumber = accountDetails.phoneNumber,
                    email = accountDetails.email,
                    password = accountDetails.password,
                    qr=null,
                    storeName = null,
                    jwtToken = jwtToken,
                    fcmToken = null,
                    bankAccount = arrayListOf(),
                    transaction = arrayListOf()
            )
        }


        fun fromPayAccount(payAccount: PayAccount,jwtToken: String?):AccountDetailsResponse{


            val entityManager = RecWalletBackendApplication.entityManagerFactory?.createEntityManager()
            val transactions = entityManager!!
                    .createNamedQuery("getTransactionsByUserID",Transaction::class.java)
                    .setParameter("id",payAccount.id)
                    .resultList


            return AccountDetailsResponse(
                    name = payAccount.userName,
                    phoneNumber = payAccount.phoneNumber,
                    email = payAccount.email,
                    password = payAccount.password,
                    qr=null,
                    storeName = null,
                    jwtToken = jwtToken,
                    fcmToken = null,
                    bankAccount = payAccount.bankAccountList,
                    transaction = transactions
            )
        }
    }

}