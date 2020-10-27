package com.DevAsh.RecWalletBackend.Service.Account

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import com.DevAsh.RecWalletBackend.Dao.OtpDao
import com.DevAsh.RecWalletBackend.Database.PayAccount
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
class AccountService(
        @Qualifier("AccountDatabase") private final val accountDao: AccountDao,
        @Qualifier("OtpDatabase") private final val otpDao: OtpDao
){


    fun isPayAccountExist(id:String):PayAccount?{
        val payAccount = accountDao.isPayAccountExist(id)
        println(payAccount)
        return if(otpDao.isVerified(id) && payAccount!=null){
            otpDao.deleteOtp(id)
            payAccount
        }else{
            null
        }
    }

    fun getMyAccount(id: String):PayAccount?{
        return  accountDao.isPayAccountExist(id)
    }

    fun getPayAccounts():List<PayAccount>{
        return accountDao.getPayAccounts()
    }

    fun addPayAccount(payAccount: PayAccount):Boolean{
        return try {
            if(otpDao.isVerified(payAccount.id!!)){
                otpDao.deleteOtp(payAccount.id!!)
                if(!accountDao.addNewPayAccount(payAccount)){
                    false
                }
                true
            }else{
                false
            }
        }catch (e:Throwable){
            e.printStackTrace()
            false
        }

    }
}