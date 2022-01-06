package com.fantasy.ladbe.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    /**
     * 외부에 Authorization 이라는 헤더의 이름으로 추가해서 보낼 수 있도록 허용해주는 메소드
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedHeaders("Authorization")
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // html 접근
        registry.addResourceHandler("/htmlResources/**")
            .addResourceLocations("classpath:/htmlResources/")
        // image 접근
        registry.addResourceHandler("/player/**")
//            .addResourceLocations("file:///Users/juyohan/Downloads/players2/") // local로 접근
            .addResourceLocations("classpath:/playerImages/") // class resource로 접근
    }
}
