package com.quyt.androidbasekotlin.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quyt.androidbasekotlin.data.datasource.local.dao.PostDao
import com.quyt.androidbasekotlin.data.datasource.local.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
