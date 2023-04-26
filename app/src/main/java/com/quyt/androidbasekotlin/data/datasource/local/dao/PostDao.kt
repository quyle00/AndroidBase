package com.quyt.androidbasekotlin.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.quyt.androidbasekotlin.data.datasource.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    suspend fun getAllPost(): List<PostEntity>

    @Insert
    suspend fun insert(post: PostEntity)
}