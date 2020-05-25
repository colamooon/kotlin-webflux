package com.colamooon.app.api.post

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class PostRouter {

    @Bean
    fun postRoute(handler: PostHandler) = coRouter {
        accept(APPLICATION_JSON).nest {
            GET("/post/{id}", handler::getPost)
            GET("/post", handler::listPosts)
            GET("/hello", handler::hello)
        }
        POST("/post", handler::createPost)
    }
}