package com.quyt.androidbasekotlin.data.datasource.local

import androidx.room.withTransaction
import com.quyt.androidbasekotlin.data.datasource.local.database.AppDatabase
import com.quyt.androidbasekotlin.data.datasource.local.entity.toPost
import com.quyt.androidbasekotlin.data.datasource.local.entity.toPostEntity
import com.quyt.androidbasekotlin.domain.model.Post

class PostLocalDatasourceImpl(private val appDatabase: AppDatabase) : PostLocalDatasource {

    override suspend fun insertPost(news: List<Post>) {
        appDatabase.withTransaction {
            appDatabase.postDao().deleteAllPost()
            news.map {
                appDatabase.postDao().insert(it.toPostEntity())
            }
        }
    }

    override suspend fun getAllPost(): List<Post> {
        val localNews = appDatabase.postDao().getAllPost()
        val listNews = mutableListOf<Post>()
        localNews.map {
            listNews.add(it.toPost())
        }
        return listNews
    }

}
