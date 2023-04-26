package com.quyt.androidbasekotlin.data.datasource.local

import com.quyt.androidbasekotlin.domain.model.Post


interface PostLocalDatasource {
   suspend fun insertPost(news: List<Post>)
   suspend fun getAllPost(): List<Post>
}
