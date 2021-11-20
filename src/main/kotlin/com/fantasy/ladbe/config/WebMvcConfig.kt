package com.fantasy.ladbe.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

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
