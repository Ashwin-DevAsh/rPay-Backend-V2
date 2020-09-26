package com.DevAsh.RecWalletBackend.Database

import javax.persistence.Entity


@Entity
class BusinessAccount : PayAccount{
    constructor()
    constructor
            (id: String?, userName: String?, phoneNumber: String?, email: String?, fcmToken: String?, password: String?, storeName: String?)
            : super(id, userName, phoneNumber, email, fcmToken, password) {
        this.storeName = storeName
    }


    var storeName:String?=null
    var isActive:Boolean?=false
}