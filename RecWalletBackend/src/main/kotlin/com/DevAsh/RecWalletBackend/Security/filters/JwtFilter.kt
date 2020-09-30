package com.DevAsh.RecWalletBackend.Security.filters

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import io.jsonwebtoken.Jwts
import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class JwtFilter(
        private var secretKey: String,
        private var accountDao: AccountDao
) : HttpFilter() {



    override fun doFilter(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?
    ) {
        val header = request?.getHeader("token")
        println("filtering ...$header")
        if (header == null) {
            println("Blocked....")
            response?.status = HttpServletResponse.SC_UNAUTHORIZED
        } else if (!checkJwt(header)) {
            println("Blocked....")
            response?.status = HttpServletResponse.SC_UNAUTHORIZED
        } else {
            println("Allowed....")
            super.doFilter(request, response, chain)
        }
    }

    private fun checkJwt(jwtString: String): Boolean {
        try {
            val id: String? = Jwts.parser().setSigningKey(secretKey.toByteArray()).parseClaimsJws(jwtString).body["id"] as String?
            if(accountDao.isPayAccountExist(id!!)==null){
                return false
            }
            return true
        } catch (e: Throwable) {
            return false
        }
    }
}
