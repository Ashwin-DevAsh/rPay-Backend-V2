package com.DevAsh.RecWalletBackend.Api.Response

import com.DevAsh.RecWalletBackend.Api.Request.AccountDetailsRequest
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
        var id:String?,
        var jwtToken: String?,
        var balance:Number?,
        var bankAccount: List<BankAccount>,
        var transaction: List<Transaction>
) : AccountDetailsRequest(
        name,
        phoneNumber,
        email,
        password,
        fcmToken,
        qr,
        storeName
) {

    companion object{
        fun fromAccountDetails(
                accountDetailsRequest: AccountDetailsRequest,
                jwtToken: String?,
                id:String
        ):AccountDetailsResponse{
            return AccountDetailsResponse(
                    name = accountDetailsRequest.name,
                    phoneNumber = accountDetailsRequest.phoneNumber,
                    email = accountDetailsRequest.email,
                    password = accountDetailsRequest.password,
                    qr=null,
                    balance = 0,
                    id = id,
                    storeName = null,
                    jwtToken = jwtToken,
                    fcmToken = null,
                    bankAccount = arrayListOf(),
                    transaction = arrayListOf()
            )
        }


        fun fromPayAccount(payAccount: PayAccount,jwtToken: String?):AccountDetailsResponse{


//            val entityManager = RecWalletBackendApplication.entityManagerFactory?.createEntityManager()
//            val transactions = entityManager!!
//                    .createNamedQuery("getTransactionsByUserID",Transaction::class.java)
//                    .setParameter("id",payAccount.id)
//                    .resultList

//            println("transactions = $transactions")

            return AccountDetailsResponse(
                    name = payAccount.userName,
                    phoneNumber = payAccount.phoneNumber,
                    email = payAccount.email,
                    balance = payAccount.balance,
                    id = payAccount.id,
                    password = payAccount.password,
                    qr=null,
                    storeName = null,
                    jwtToken = jwtToken,
                    fcmToken = null,
                    bankAccount = payAccount.bankAccountList,
                    transaction = ArrayList()
            )
        }
    }

    override fun toString(): String {
        return "AccountDetailsResponse(jwtToken=$jwtToken, bankAccount=$bankAccount, transaction=$transaction)"
    }


}