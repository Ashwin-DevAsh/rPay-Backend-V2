package com.DevAsh.RecWalletBackend.Dao.PostgresDatabse

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import com.DevAsh.RecWalletBackend.Dao.OtpDao
import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.Otp
import com.DevAsh.RecWalletBackend.Database.PayAccount
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Repository("AccountDatabase")
class AccountDatabase(private val entityManagerFactory: EntityManagerFactory) :  AccountDao {

    var entityManager: EntityManager = entityManagerFactory.createEntityManager()

    override fun isPayAccountExist(id: String): PayAccount? {
        val payAccount = entityManager.find(PayAccount::class.java,id)
        println("PayAccountExist = $payAccount")
        return payAccount
    }

    override fun isBusinessAccountExist(id: String): BusinessAccount? {
        TODO("Not yet implemented")
    }

    override fun addNewPayAccount(payAccount: PayAccount):Boolean {
        return try {
            entityManager.transaction.begin()
            entityManager.persist(payAccount)
            entityManager.transaction.commit()
            false
        }catch (e:Throwable){
            e.printStackTrace()
            entityManager.transaction.rollback()
            true
        }

    }

    override fun addNewBusinessAccount(businessAccount: BusinessAccount):Boolean {
        return try {
            entityManager.transaction.begin()
            entityManager.persist(businessAccount)
            entityManager.transaction.commit()
            false
        }catch (e:Throwable){
            e.printStackTrace()
            entityManager.transaction.rollback()
            true
        }

    }

    override fun getPayAccounts(): List<PayAccount> {
        val payAccounts = entityManager.createNamedQuery("getPayAccounts",PayAccount::class.java).resultList
        println(payAccounts)
        return payAccounts
    }
}