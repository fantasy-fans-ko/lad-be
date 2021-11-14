package com.fantasy.ladbe.config

import com.fantasy.ladbe.oauth.handler.OAuth2SuccessHandler
import com.fantasy.ladbe.oauth.service.CustomOAuth2UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

@Configuration
class SecurityConfig(
    private val successHandler: OAuth2SuccessHandler,
    private val oAuth2UserService: CustomOAuth2UserService,
    ) :
    WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .anyRequest().authenticated()

            .and()
            .oauth2Login()
            .successHandler(successHandler)
            .userInfoEndpoint().userService(oAuth2UserService);

    }

}