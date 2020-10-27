package com.DevAsh.RecWalletBackend.Dao

import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.FromType
import com.DevAsh.RecWalletBackend.Database.Transactions.ToType
import com.DevAsh.RecWalletBackend.Database.Transactions.Transaction
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.ExternalSource
import javax.persistence.Id


interface TransactionDao {
    fun makeTransaction(transaction: Transaction): Transaction?
}