package com.colamooon.app.api.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.server.ServerWebInputException
import reactor.core.publisher.Mono

@Component
class PostHandler(private val postRepository: PostRepository) {

    private val validator = PostValidator()

    suspend fun listPosts(request: ServerRequest): ServerResponse {
        val posts: Flow<PostView> = postRepository.findAll().map(this::entityToView).asFlow()
        return ok().contentType(APPLICATION_JSON).bodyAndAwait(posts)
    }

    suspend fun createPost(request: ServerRequest): ServerResponse {
        val post = request.awaitBody<Post>()
        validate(post)
        postRepository.save(post)
        return ok().buildAndAwait()
    }


    suspend fun getPost(request: ServerRequest): ServerResponse {
        val postId = request.pathVariable("id").toLong()
        return postRepository.findById(postId)?.let {
            ok().contentType(APPLICATION_JSON).bodyValueAndAwait(it)
        } ?: ServerResponse.notFound().buildAndAwait()
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    suspend fun hello(request: ServerRequest): ServerResponse {
        return ok().contentType(APPLICATION_JSON).bodyValueAndAwait("hello")
    }


    fun entityToView(post: Post): PostView {
        val postView = PostView()
        postView.getView(post)
        return postView
    }

    private fun validate(post: Post) {
        val errors: Errors = BeanPropertyBindingResult(post, "post");
        validator.validate(post, errors);
        if (errors.hasErrors()) {
            throw ServerWebInputException(errors.toString())
        }
    }


}