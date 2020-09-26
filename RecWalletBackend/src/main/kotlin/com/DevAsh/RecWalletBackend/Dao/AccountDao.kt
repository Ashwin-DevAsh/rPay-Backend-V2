package com.DevAsh.RecWalletBackend.Dao

import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount

interface AccountDao {
    fun isPayAccountExist(id:String):PayAccount?
    fun isBusinessAccountExist(id:String):BusinessAccount?
    fun addNewPayAccount(payAccount: PayAccount): Boolean
    fun addNewBusinessAccount(businessAccount: BusinessAccount): Boolean
}