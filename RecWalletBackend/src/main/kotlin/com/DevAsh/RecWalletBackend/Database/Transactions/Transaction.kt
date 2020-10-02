package com.DevAsh.RecWalletBackend.Database.Transactions

import jdk.jfr.DataAmount
import java.sql.Timestamp
import javax.persistence.*

@Entity
@NamedQuery(
        name = "getTransactionsByUserID",
        query = "select t from Transaction t where (t.from.payAccount.id=:id or t.to.payAccount.id=:id) and t.message is Null"
)
class Transaction {
    constructor()
    constructor(from: FromType?, to: ToType?, amount: Number,message: String?) {
        this.from = from
        this.to = to
        this.amount = amount
        this.message = message
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    var id: Int?=null;
    @OneToOne
    var from: FromType?=null;
    @OneToOne
    var to: ToType?=null;
    var amount:Number?=null;
    var timestamp:Timestamp= Timestamp(System.currentTimeMillis())
    var message:String?=null;


    override fun toString(): String {
        return "Transaction(id=$id, from=$from, to=$to, amount=$amount, timestamp=$timestamp, message=$message)"
    }


}