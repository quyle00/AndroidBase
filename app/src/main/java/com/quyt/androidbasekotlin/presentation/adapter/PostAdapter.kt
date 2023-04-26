package com.quyt.androidbasekotlin.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.ItemPostBinding
import com.quyt.androidbasekotlin.domain.model.Post

class PostAdapter() : RecyclerView.Adapter<PostViewHolder>() {
    private val mListPost = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = DataBindingUtil.inflate<ItemPostBinding>(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mListPost.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(mListPost[position])
    }

    fun setListPost(listPost: List<Post>) {
        mListPost.clear()
        mListPost.addAll(listPost)
        notifyDataSetChanged()
    }
}

class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        Glide.with(binding.root.context).load(post.image).into(binding.ivImage)
        binding.tvCaption.text = post.text
    }
}
