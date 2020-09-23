package com.DevAsh.RecWalletBackend.Database.Transactions

import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.ExternalSource
import javax.persistence.*

@Entity
class FromType() {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    var id: Int? = null
    @ManyToOne
    var payAccount: PayAccount?=null;
    @ManyToOne
    var externalSource: ExternalSource?=null;
    @ManyToOne
    var businessAccount:BusinessAccount?=null;
}