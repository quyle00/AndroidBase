package com.quyt.androidbasekotlin.data.datasource.remote.model

import com.quyt.androidbasekotlin.domain.model.Post

class PostResponse {
    var id: String = ""
    var text: String? = null
    var image: String? = null
    var tags: List<String>? = null
    var publishDate: String? = null
}

fun PostResponse.toPost(): Post {
    return Post(
        id = this.id,
        text = this.text,
        image = this.image,
        tags = this.tags,
        publishDate = this.publishDate,
    )
}