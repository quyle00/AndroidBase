package com.quyt.androidbasekotlin.domain.repository

import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.domain.model.Result

interface PostRepository {
    suspend fun getPosts(page: Int, limit: Int): Result<List<Post>>
}