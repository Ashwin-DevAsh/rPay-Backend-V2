package com.DevAsh.RecWalletBackend.Dao.PostgresDatabse

import com.DevAsh.RecWalletBackend.Dao.TransactionDao
import com.DevAsh.RecWalletBackend.Database.Transactions.Transaction
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.ExternalSource
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Id
import javax.persistence.NamedQuery

@Repository("TransactionDatabase")
class TransactionDatabase(private val entityManagerFactory: EntityManagerFactory):TransactionDao {

    var entityManager: EntityManager = entityManagerFactory.createEntityManager()

    override fun makeTransaction(transaction: Transaction):Transaction? {
        return try {
            entityManager.persist(transaction)
            transaction
        }catch (e:Throwable){
            e.printStackTrace()
            null
        }
    }
}