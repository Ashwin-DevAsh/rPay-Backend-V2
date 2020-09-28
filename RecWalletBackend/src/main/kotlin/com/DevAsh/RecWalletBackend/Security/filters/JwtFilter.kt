package com.DevAsh.RecWalletBackend.Security.filters

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import io.jsonwebtoken.Jwts
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtFilter() : HttpFilter() {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Autowired
    @Qualifier("AccountDatabase")
    lateinit var accountDao: AccountDao

    override fun doFilter(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?
    ) {
        val header = request?.getHeader("token")
        println("filtering ..." + header)
        if (header == null) {
            response?.status = HttpServletResponse.SC_UNAUTHORIZED
        } else if (!checkJwt(header)) {
            response?.status = HttpServletResponse.SC_UNAUTHORIZED
        } else {
            super.doFilter(request, response, chain)
        }
    }

    fun checkJwt(jwtString: String): Boolean {
        try {
            val id: String? = Jwts.parser().setSigningKey(secretKey.toByteArray()).parseClaimsJws(jwtString).body["id"] as String?
            if (accountDao.isPayAccountExist(id!!) != null) {
                return true
            }
            return false
        } catch (e: Throwable) {
            return false
        }
    }
}
