package com.DevAsh.RecWalletBackend.Service.Transaction

import com.DevAsh.RecWalletBackend.Api.Request.AddMoneyDetails
import com.DevAsh.RecWalletBackend.Dao.AccountDao
import com.DevAsh.RecWalletBackend.Dao.TransactionDao
import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.PayAccount
import com.DevAsh.RecWalletBackend.Database.Transactions.FromType
import com.DevAsh.RecWalletBackend.Database.Transactions.ToType
import com.DevAsh.RecWalletBackend.Database.Transactions.Transaction
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Service
class TransactionService(
        val transactionDao: TransactionDao,
        val accountDao: AccountDao,
        private final val entityManagerFactory: EntityManagerFactory
) {

    var entityManager: EntityManager = entityManagerFactory.createEntityManager()


    fun addMoney(id:String,addMoneyDetails: AddMoneyDetails):Transaction?{
        entityManager.transaction.begin()
        try {
            val account:Any?
            account = if (id.startsWith("rPay")){
                getPayAccount(id)
            }else{
                getBusinessAccount(id)
            }

            account?.balance = account?.balance?.toLong()!! + addMoneyDetails.amount.toLong()
            val transaction = transactionDao.makeTransaction( Transaction(
                    from = FromType(addMoneyDetails.externalSource),
                    to = ToType(account),
                    amount = addMoneyDetails.amount,
                    message = null
            ))
            if (transaction==null){
                entityManager.transaction.rollback()
                return null
            }
            entityManager.transaction.commit()
            return transaction
        }catch (e:Throwable){
            entityManager.transaction.rollback()
            return null
        }
    }




    fun getBusinessAccount(id: String):BusinessAccount?{
        return accountDao.isBusinessAccountExist(id)
    }


    fun getPayAccount(id: String):PayAccount?{
       return accountDao.isPayAccountExist(id)
    }


}

