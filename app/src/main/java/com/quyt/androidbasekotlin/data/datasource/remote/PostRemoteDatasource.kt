package com.quyt.androidbasekotlin.data.datasource.remote

import com.quyt.androidbasekotlin.data.datasource.remote.model.ListPostResponse
import com.quyt.androidbasekotlin.domain.model.Post


interface PostRemoteDatasource {
    suspend fun getPosts(page: Int, limit: Int): ListPostResponse
    suspend fun getPostDetail(id: String) : Post
}