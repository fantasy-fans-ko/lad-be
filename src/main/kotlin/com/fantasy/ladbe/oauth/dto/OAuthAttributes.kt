package com.fantasy.ladbe.oauth.dto

/**
 * OAuth2 로그인을 했을 때, 해당 서버에서 받아와 매핑할 객체
 * attributes : 서버에서 받아온 사용자 정보들
 * nameAttributeKey : 소셜 로그인에서 사용하는 기본 키에 대한 정보
 * name : 사용자 이름
 * email : 사용자 이메일
 * imagePath : 사용자 프사 경로
 * code : 카카오 서버에서 주어지는 사용자 고유 번호
 */
class OAuthAttributes(
    val attributes: Map<String, Any> = emptyMap(),
    val nameAttributeKey: String = "",
    val name: String = "",
    val email: String = "",
    val imagePath: String = "",
    val code: Long = 0L,
) {

    companion object {
        /**
         * 소셜 로그인을 구분
         * param : registerId - 소셜 로그인의 종류
         *         usernameAttributeKey - 사용자의 Attribute의 Key의 이름 (kakao = id)
         *         attributes - 사용자의 정보를 받아올 정보
         * return : registerId에 의해 각 소셜 로그인 함수로 접근.
         *          registerId의 값이 없다면 빈 객체를 반환
         */
        fun of(
            registerId: String,
            usernameAttributeKey: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            return when (registerId) {
                "kakao" -> ofKakao(usernameAttributeKey, attributes)
                "yahoo" -> ofYahoo(usernameAttributeKey, attributes)
                else -> OAuthAttributes()
            }
        }

        /**
         * 카카오에서 받아온 정보들을 원하는 데이터들을 추추해온다.
         * param : usernameAttributeKey - 사용자의 Attribute의 Key의 이름 (kakao = id)
         *         attributes - 카카오에서 받은 사용자 정보
         * return : this
         */
        fun ofKakao(
            usernameAttributeKey: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            val kakaoAccount: Map<String, Any> = attributes["kakao_account"] as Map<String, Any>
            val profile: Map<String, Any> = kakaoAccount["profile"] as Map<String, Any>

            return OAuthAttributes(
                attributes = attributes,
                nameAttributeKey = usernameAttributeKey,
                name = profile["nickname"] as String,
                imagePath = profile["thumbnail_image_url"] as String,
                email = kakaoAccount["email"] as String,
                code = attributes["id"].toString().toLong()
            )
        }

        fun ofYahoo(
            usernameAttributeKey: String,
            attributes: Map<String, Any>
        ) : OAuthAttributes {
            println("sdfsdfsdf")
            return OAuthAttributes()
        }
    }
}
