package com.DevAsh.RecWalletBackend.Api.Response

import com.DevAsh.RecWalletBackend.Api.Request.AccountDetails
import com.DevAsh.RecWalletBackend.Database.PayAccount

class AccountDetailsResponse(
        name: String?,
        phoneNumber: String?,
        email: String?,
        password: String?,
        fcmToken: String?,
        qr: String?,
        storeName: String?,
        var jwtToken: String?
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
                    fcmToken = null
            )
        }


        fun fromPayAccount(payAccount: PayAccount,jwtToken: String?):AccountDetailsResponse{
            return AccountDetailsResponse(
                    name = payAccount.userName,
                    phoneNumber = payAccount.phoneNumber,
                    email = payAccount.email,
                    password = payAccount.password,
                    qr=null,
                    storeName = null,
                    jwtToken = jwtToken,
                    fcmToken = null
            )
        }
    }

}