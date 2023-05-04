package com.quyt.androidbasekotlin.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.quyt.androidbasekotlin.domain.model.Post

class PostDiffUtilsCallback(private val oldPosts : List<Post>,private val newPosts : List<Post>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldPosts.size
    }

    override fun getNewListSize(): Int {
        return newPosts.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition].id == newPosts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPosts[oldItemPosition] == newPosts[newItemPosition]
    }
}