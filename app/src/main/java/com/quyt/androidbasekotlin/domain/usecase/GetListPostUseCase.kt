package com.quyt.androidbasekotlin.domain.usecase

import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.domain.model.Result
import com.quyt.androidbasekotlin.domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetListPostUseCase (private val postRepository: PostRepository) {

    suspend operator fun invoke(page: Int, limit: Int): Result<List<Post>> {
        return withContext(Dispatchers.IO){
            postRepository.getPosts(page, limit)
        }
    }
}