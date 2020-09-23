package com.DevAsh.RecWalletBackend.Database.Transactions.Types


import javax.persistence.*

@Entity
class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id:String?=null;
    var ifsc:String?=null;
    var accountNumber:String?=null;
    var holderName:String?=null;
    var bankName:String?=null;
}