package com.DevAsh.RecWalletBackend.Api.Request

import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount

open class AccountDetails(
        var name: String?,
        var phoneNumber: String?,
        var email: String?,
        var password: String?,
        var fcmToken: String?,
        var qr: String?,
        var storeName: String?
) {

    fun toPayAccount():PayAccount{
        return PayAccount(
                userName= name,
                id="rPay@$phoneNumber",
                email=email,
                password=password,
                fcmToken=fcmToken,
                phoneNumber = phoneNumber
        )
    }

    fun toBusinessAccount():BusinessAccount{
        return BusinessAccount(
                userName= name,
                id="rBusiness@$phoneNumber",
                email=email,
                password=password,
                fcmToken=fcmToken,
                phoneNumber = phoneNumber,
                storeName=storeName
        )
    }
}
