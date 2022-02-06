package com.fantasy.ladbe.oauth.jwt

import com.fantasy.ladbe.dto.UserDto
import com.fantasy.ladbe.handler.exception.Exceptions
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.Claims
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import java.lang.IllegalArgumentException
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Base64
import java.util.Date
import javax.servlet.http.HttpServletRequest

const val ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS = 3 * 60 * 60 * 1000L // 3시간

@Component
class JwtProvider(
    @Value("\${jwt.secret}")
    val jwtSecret: String
) : InitializingBean {

    private lateinit var key: Key
    val secret: String = Base64.getEncoder().encodeToString(jwtSecret.toByteArray(StandardCharsets.UTF_8))

    override fun afterPropertiesSet() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
    }

    /**
     * Jwt 를 생성하는 메소드
     * param :  oAuth2Dto - OAuth2User 를 맵핑한 Dto 의 정보가 들어있는 객체
     * return : Jwt 를 반환한다.
     */
    fun create(
        oAuth2Dto: UserDto.Response.OAuth2UserDetail
    ): String {
        // 유효시간 설정
        val now: Long = Date().time
        val validity = Date(now + ACCESS_TOKEN_VALIDITY_IN_MILLISECONDS)

        return Jwts.builder()
            .setSubject(oAuth2Dto.kakaoName) // 카카오톡에서 사용하고 있는 이름
            .claim("auth", oAuth2Dto.role) // 권한의 대한 정보
            .claim("email", oAuth2Dto.kakaoEmail) // 이메일 정보
            .setIssuedAt(Date(now)) // 현재 시간
            .signWith(key, SignatureAlgorithm.HS512)
            .setExpiration(validity) // 기한 설정
            .compact()
    }

    /**
     * 토큰을 파싱하는 메소드
     * param : token - 요청 헤더에 담겨온 토큰 정보
     * return : Authentication - 권한의 정보
     */
    fun parseJwt(
        token: String
    ): Authentication {
        val claims: Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build().parseClaimsJws(token).body

        val authorities: Collection<out GrantedAuthority> = claims["auth"].toString().split(",").map { authority ->
            SimpleGrantedAuthority(authority)
        }.toList()

        val principal: UserDetails = User(claims["email"].toString(), "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    /**
     * 토큰의 유효성을 검사하는 메소드
     * param : token - 요청 헤더에서 가져온 jwt 의 값
     * return : 파싱했을 때, 아무 문제가 없다면 true 반환
     *          문제가 생겼을 경우, AuthenticationEntryPoint 로 에러를 던져준다.
     */
    fun validateToken(token: String, request: HttpServletRequest): Boolean {
        runCatching {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
        }.onSuccess {
            return true
        }.onFailure { e ->
            when (e) {
                is SecurityException, is MalformedJwtException ->
                    request.setAttribute("exception", Exceptions.JWT_WRONG_TYPE_TOKEN.code)
                is ExpiredJwtException -> request.setAttribute("exception", Exceptions.JWT_EXPIRED_TOKEN.code)
                is UnsupportedJwtException -> request.setAttribute("exception", Exceptions.JWT_UNSUPPORTED_TOKEN.code)
                is IllegalArgumentException -> request.setAttribute("exception", Exceptions.JWT_WRONG_TOKEN.code)
                else -> {
                    request.setAttribute("exception", Exceptions.JWT_UNKNOWN_ERROR)
                    request.setAttribute("error", e.message)
                }
            }
            throw e // 에러를 던져주지 않으면 AuthenticationEntryPoint 로 넘어가지 않음.
        }
        return false
    }
}
