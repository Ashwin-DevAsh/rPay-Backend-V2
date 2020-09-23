package com.DevAsh.RecWalletBackend.Dao

import com.DevAsh.RecWalletBackend.Database.Otp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Repository("postgres")
class PostgresDatabase(
        private final val entityManagerFactory: EntityManagerFactory
) :OtpDao{


    private final var entityManager:EntityManager?=null

    init {
        this.entityManager=entityManagerFactory.createEntityManager()
    }



    override fun insertOtp(id: String, number: String, otp: String, type: String): Boolean {
        try {
            val otp:Otp?= Otp(
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
            entityManager!!.persist(otp)
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


}