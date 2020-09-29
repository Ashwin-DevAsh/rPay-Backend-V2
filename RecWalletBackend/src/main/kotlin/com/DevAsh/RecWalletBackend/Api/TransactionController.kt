package com.DevAsh.RecWalletBackend.Api

import com.DevAsh.RecWalletBackend.Api.Request.AddMoneyDetails
import com.DevAsh.RecWalletBackend.Database.Transactions.Transaction
import com.DevAsh.RecWalletBackend.Database.Transactions.Types.ExternalSource
import com.DevAsh.RecWalletBackend.Service.Transaction.TransactionService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/v1/transaction/protected")
class TransactionController(
       val transactionService: TransactionService
) {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @PostMapping("/addMoney")
    fun addMoney(@RequestBody addMoneyDetails: AddMoneyDetails, request: HttpServletRequest, response: HttpServletResponse):Transaction?{
        val transaction =  transactionService.addMoney(getId(request), addMoneyDetails)
        if(transaction==null){
            response.status = HttpServletResponse.SC_PAYMENT_REQUIRED
        }else{
            response.status = HttpServletResponse.SC_ACCEPTED
        }
        return transaction
    }

    fun getId(request: HttpServletRequest): String {
        val header = request.getHeader("token")
        return Jwts.parser().setSigningKey(secretKey.toByteArray()).parseClaimsJws(header).body["id"] as String
    }

}