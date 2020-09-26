package com.DevAsh.RecWalletBackend.Dao.PostgresDatabse

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import com.DevAsh.RecWalletBackend.Dao.OtpDao
import com.DevAsh.RecWalletBackend.Database.BusinessAccount
import com.DevAsh.RecWalletBackend.Database.Otp
import com.DevAsh.RecWalletBackend.Database.PayAccount
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Repository("OtpDatabase")
class OtpDatabase(private val entityManagerFactory: EntityManagerFactory) : OtpDao {

    private final var entityManager:EntityManager?=null

    init {
        this.entityManager=entityManagerFactory.createEntityManager()
    }

    override fun insertOtp(id: String, number: String, otp: String, type: String): Boolean {
        try {
            val otpObject:Otp?= Otp(
                    accountId = id,
                    number = number,
                    otp = otp,
                    type = type,
                    isVerified = false
            )
            if(!deleteOtp(id)){
                return false
            }
            entityManager!!.transaction.begin()
            entityManager!!.persist(otpObject)
            entityManager!!.transaction.commit()
            return true
        }catch (e:Throwable){
            println(e.printStackTrace())
            entityManager!!.transaction.rollback()
            return false
        }


    }

    override fun verifyOtp(id: String, otp: String, type: String): Boolean {
        try {
            val otpObject:Otp? = entityManager!!.find(Otp::class.java,id)
            if(otpObject?.otp==otp){
                entityManager!!.transaction.begin()
                otpObject.isVerified=true
                entityManager!!.transaction.commit()
                return true
            }
            return false
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }

    override fun deleteOtp(id: String): Boolean {
        return try {
            val otp = entityManager?.find(Otp::class.java,id)
            if(otp!=null){
                entityManager!!.transaction.begin()
                entityManager!!.remove(otp)
                entityManager!!.transaction.commit()
            }
            true
        }catch (e:Exception){
            e.printStackTrace()
            entityManager!!.transaction.rollback()
            false
        }
    }

    override fun isVerified(id: String): Boolean {
        val otp =  entityManager!!.find(Otp::class.java,id)
        println(otp)
        return otp?.isVerified ?: false
    }




}