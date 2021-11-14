package com.fantasy.ladbe.oauth.service

import com.fantasy.ladbe.oauth.token.AuthToken
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import io.jsonwebtoken.Jwts

import io.jsonwebtoken.Claims

import io.jsonwebtoken.Jws
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.token.Token
import java.lang.Exception
import java.util.*


@Service
class TokenService {

    private var secretkey: String = "token-secret-key"

    @PostConstruct
    protected fun init() {
        secretkey = Base64.getEncoder().encodeToString(secretkey.toByteArray())
    }

    fun generateToken(uid: String?, role: String): AuthToken {

        val now = Date()
        val tokenPeriod = 1000L * 60L * 10L
        val refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L

        val claims = Jwts.claims().setSubject(uid)
        claims["role"] = role

        return AuthToken(
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(now.getTime() + tokenPeriod))
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact(),
            Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(now.getTime() + refreshPeriod))
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact())

    }

    fun verifyToken(token: String): Boolean {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(secretkey)
                .parseClaimsJws(token)
            claims.body
                .expiration
                .after(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUid(token: String): String {
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).body.subject
    }


}
