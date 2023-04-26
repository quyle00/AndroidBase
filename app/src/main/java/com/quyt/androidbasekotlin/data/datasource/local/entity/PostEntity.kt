package com.quyt.androidbasekotlin.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.quyt.androidbasekotlin.domain.model.Post

@Entity(tableName = "Post")
data class PostEntity(
    @PrimaryKey
    var id: String,
    var text: String? ,
    var image: String? ,
    var tags: String? ,
    var publishDate: String?,
)

fun PostEntity.toPost(): Post {
    return Post(
        id = this.id,
        text = this.text,
        image = this.image,
        tags = Gson().fromJson(this.tags, Array<String>::class.java).toList(),
        publishDate = this.publishDate,
    )
}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = this.id,
        text = this.text,
        image = this.image,
        tags = Gson().toJson(this.tags),
        publishDate = this.publishDate,
    )
}