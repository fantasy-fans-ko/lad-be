package com.fantasy.ladbe.oauth.service

import org.springframework.util.SerializationUtils
import java.util.Base64
import java.util.Optional

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CookieUtils {
    companion object {
        fun getCookie(
            request: HttpServletRequest,
            name: String,
        ): Optional<Cookie> {
            val cookies = request.cookies
            if (!cookies.isNullOrEmpty()) {
                for (cookie: Cookie in cookies) {
                    if (cookie.name.equals(name)) return Optional.of(cookie)
                }
            }
            return Optional.empty()
        }

        fun addCookie(
            response: HttpServletResponse,
            name: String,
            value: String,
            maxAge: Int,
        ) {
            val cookie = Cookie(name, value)
            cookie.path = "/"
            cookie.maxAge = maxAge
            cookie.isHttpOnly = true
            response.addCookie(cookie)
        }

        fun deleteCookie(
            request: HttpServletRequest,
            response: HttpServletResponse,
            name: String,
        ) {
            val cookies = request.cookies
            if (!cookies.isNullOrEmpty()) {
                for (cookie: Cookie in cookies) {
                    if (cookie.name.equals(name)) {
                        cookie.path = "/"
                        cookie.maxAge = 0
                        cookie.value = ""
                        response.addCookie(cookie)
                    }
                }
            }
        }

        fun serialize(
            obj: Any,
        ): String = Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj))

        fun <T> deserialize(
            cookie: Cookie,
            cls: Class<in T>,
        ): T = cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.value))) as T
    }
}
