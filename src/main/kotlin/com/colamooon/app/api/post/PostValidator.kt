package com.colamooon.app.api.post

import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator


class PostValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return Post::class.java == clazz
    }

    override fun validate(obj: Any, e: Errors) {
        ValidationUtils.rejectIfEmpty(e, "title", "title.empty")
    }
}