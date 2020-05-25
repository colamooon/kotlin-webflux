package com.colamooon.app.api.post


import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long> {

}
