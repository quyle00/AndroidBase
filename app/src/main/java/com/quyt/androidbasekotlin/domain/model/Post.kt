package com.quyt.androidbasekotlin.domain.model


data class Post(
    var id: String,
    var text: String?,
    var image: String?,
    var tags: List<String>?,
    var publishDate: String?,
)

