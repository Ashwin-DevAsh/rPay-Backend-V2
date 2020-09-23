package com.DevAsh.RecWalletBackend.Database.Transactions.Types

import javax.persistence.Entity
import javax.persistence.Id


@Entity
class ExternalSource {
    @Id
    var id:String?=null;
    var type:String?=null;
}