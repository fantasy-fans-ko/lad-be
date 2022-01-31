package com.fantasy.ladbe.config

import com.fantasy.ladbe.oauth.handler.OAuth2SuccessHandler
import com.fantasy.ladbe.oauth.jwt.JwtProvider
import com.fantasy.ladbe.oauth.jwt.JwtAccessDeniedHandler
import com.fantasy.ladbe.oauth.jwt.JwtAuthenticationEntryPoint
import com.fantasy.ladbe.oauth.jwt.JwtFilter
import com.fantasy.ladbe.oauth.service.CustomOAuth2UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    val customOAuth2UserService: CustomOAuth2UserService,
    val oAuth2SuccessHandler: OAuth2SuccessHandler,

    val jwtProvider: JwtProvider,
    val authenticationEntrypoint: JwtAuthenticationEntryPoint,
    val accessDeniedException: JwtAccessDeniedHandler
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().disable()
            .formLogin().disable()
            .authorizeRequests()
            .antMatchers( // 로그인은 누구나 접근 가능
                "/oauth2/authorization/kakao"
            ).permitAll()
            .antMatchers( // 이외의 경로는 권한을 가지고 있어야 함.
                "/api/players/**"
            ).hasAnyRole("USER")
            .antMatchers( // 추가적인 선수의 데이터 주입 방지를 위해 막아야함.
                "/htmlResources/**",
                "/api/scraping/players",
            ).permitAll()
//            .hasAnyRole("ADMIN")
            .anyRequest()
            .authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedException)
            .authenticationEntryPoint(authenticationEntrypoint)
            .and()
            .oauth2Login()
            .userInfoEndpoint().userService(customOAuth2UserService)
            .and()
            .successHandler(oAuth2SuccessHandler)
            .and()
            .logout()
            .clearAuthentication(true)
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .and()
            .addFilterBefore(JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter::class.java)
    }
}
