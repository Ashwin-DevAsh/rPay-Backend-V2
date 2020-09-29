package com.DevAsh.RecWalletBackend.Security

import com.DevAsh.RecWalletBackend.Dao.AccountDao
import com.DevAsh.RecWalletBackend.Security.filters.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.crypto.SecretKey

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    @Autowired
    lateinit var accountDao: AccountDao

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .antMatcher("/**/protected/**")
                .addFilterBefore(JwtFilter(secretKey,accountDao), BasicAuthenticationFilter::class.java)
    }
}
