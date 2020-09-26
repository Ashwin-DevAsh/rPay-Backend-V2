package com.DevAsh.RecWalletBackend.Security.filters

import javax.servlet.FilterChain
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class OtpFilter: HttpFilter() {
    override fun doFilter(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?) {
        super.doFilter(request, response, chain)
    }
}