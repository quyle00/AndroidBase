package com.quyt.androidbasekotlin.data.datasource.remote.model

import com.quyt.androidbasekotlin.domain.model.Post

class ListPostResponse : BasePagingResponse() {
    val data: List<PostResponse>? = null
}

fun ListPostResponse.toPostList(): List<Post> {
    return data?.map { it.toPost() } ?: emptyList()
}