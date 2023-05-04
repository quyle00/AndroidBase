package com.quyt.androidbasekotlin.data.datasource.remote

import com.quyt.androidbasekotlin.data.datasource.remote.model.ListPostResponse
import com.quyt.androidbasekotlin.data.datasource.remote.service.PostApiService
import com.quyt.androidbasekotlin.data.datasource.remote.model.toPostList
import com.quyt.androidbasekotlin.domain.model.Post
import javax.inject.Inject

class PostRemoteDatasourceImpl @Inject constructor(private val services: PostApiService) : PostRemoteDatasource {

    override suspend fun getPosts(page: Int, limit: Int): ListPostResponse {
         try {
             return services.getPosts(page, limit)
         }catch (e: Exception){
             throw e
         }
    }

    override suspend fun getPostDetail(id: String): Post {
        try {
            return services.getPostDetail(id)
        }catch (e: Exception){
            throw e
        }
    }
}