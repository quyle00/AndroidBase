package com.quyt.androidbasekotlin.data.datasource.remote.service

import com.quyt.androidbasekotlin.data.datasource.remote.model.ListPostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApiService {

    @GET("post")
    suspend fun getPosts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ListPostResponse
}