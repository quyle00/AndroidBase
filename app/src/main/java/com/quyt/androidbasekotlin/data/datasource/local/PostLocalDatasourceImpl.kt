package com.quyt.androidbasekotlin.data.datasource.local

import com.quyt.androidbasekotlin.data.datasource.local.database.AppDatabase
import com.quyt.androidbasekotlin.data.datasource.local.entity.toPost
import com.quyt.androidbasekotlin.data.datasource.local.entity.toPostEntity
import com.quyt.androidbasekotlin.domain.model.Post

class PostLocalDatasourceImpl(private val appDatabase: AppDatabase) : PostLocalDatasource {

    override suspend fun insertPost(news: List<Post>) {
        news.map {
//            d { "insert to local data ${it.title}" }
            appDatabase.postDao().insert(it.toPostEntity())
        }
    }

    override suspend fun getAllPost(): List<Post> {
        val localNews = appDatabase.postDao().getAllPost()
//        d { "local news size ${localNews.size}" }
        val listNews = mutableListOf<Post>()
        localNews.map {
            listNews.add(it.toPost())
        }
        return listNews
    }
}
