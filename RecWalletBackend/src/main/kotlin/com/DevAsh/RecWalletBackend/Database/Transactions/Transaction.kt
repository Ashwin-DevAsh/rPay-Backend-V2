package com.DevAsh.RecWalletBackend.Database.Transactions

import java.sql.Timestamp
import javax.persistence.*

@Entity
@NamedQuery(
        name = "getTransactionsByUserID",
        query = "select t from Transaction t where (t.from.payAccount.id=:id or t.to.payAccount.id=:id) and t.message is Null"
)
class Transaction {
    constructor()
    constructor(from: FromType?, to: ToType?, timestamp: Timestamp, type: String?, message: String?) {
        this.from = from
        this.to = to
        this.timestamp = timestamp
        this.type = type
        this.message = message
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    var id: String?=null;
    @OneToOne
    var from: FromType?=null;
    @OneToOne
    var to: ToType?=null;
    var timestamp:Timestamp= Timestamp(System.currentTimeMillis())
    var type:String?=null
    var message:String?=null;




}