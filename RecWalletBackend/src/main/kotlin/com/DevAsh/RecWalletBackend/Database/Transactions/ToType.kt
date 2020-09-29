package com.DevAsh.RecWalletBackend.Database.Transactions

import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.BankAccount
import javax.persistence.*

@Entity
class ToType() {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    var id: String? = null
    @ManyToOne
    var payAccount: PayAccount?=null;
    @ManyToOne
    var bankAccount: BankAccount?=null;
    @ManyToOne
    var businessAccount:BusinessAccount?=null;

    constructor(payAccount: PayAccount?):this() {
        this.payAccount = payAccount
    }
}