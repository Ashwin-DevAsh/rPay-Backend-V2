package com.DevAsh.RecWalletBackend.Database

import com.DevAsh.RecWalletBackend.Database.Transactions.Types.BankAccount
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnoreType
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class PayAccount {
    @Id
    var id: String? = null
    var userName: String? = null

    @Column(unique = true)
    var phoneNumber: String? = null

    @Column(unique = true)
    var email: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(unique = true, nullable = true)
    var fcmToken: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password:String?=null

    @OneToMany
    var bankAccountList: List<BankAccount> = arrayListOf()

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var balance:Number=0;

    constructor() {}
    constructor(id: String?, userName: String?, phoneNumber: String?, email: String?, fcmToken: String?, password: String?) {
        this.id = id
        this.userName = userName
        this.phoneNumber = phoneNumber
        this.email = email
        this.fcmToken = fcmToken
        this.password = password
    }


}