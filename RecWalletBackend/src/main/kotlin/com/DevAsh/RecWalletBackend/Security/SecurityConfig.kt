package com.DevAsh.RecWalletBackend.Security

import com.DevAsh.RecWalletBackend.Security.filters.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var jwtFilter: JwtFilter

    override fun configure(http: HttpSecurity?) {
        http!!
                .httpBasic().disable()
                .csrf().disable()
                .antMatcher("/api/v1/*/protected/**")
                .addFilterBefore(jwtFilter,BasicAuthenticationFilter::class.java)
    }
}