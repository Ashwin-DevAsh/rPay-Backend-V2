package com.DevAsh.RecWalletBackend.Database

import org.springframework.data.jpa.repository.Query
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.NamedQuery

@Entity
@NamedQuery(
        name = "getOtpByIdAndType",
        query = "select o from Otp o where o.accountId=:id and o.type = :type"
)
class Otp {
    @Id
    var accountId:String?=null;
    var otp:String?=null;
    var number:String?=null;
    var type:String?=null;
    var isVerified:Boolean=false;

    constructor()

    constructor(accountId: String?, otp: String?, number: String?, type: String?, isVerified: Boolean) {
        this.accountId = accountId
        this.otp = otp
        this.number = number
        this.type = type
        this.isVerified = isVerified
    }
}