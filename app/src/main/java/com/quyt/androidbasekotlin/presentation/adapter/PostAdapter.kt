package com.quyt.androidbasekotlin.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.ItemPostBinding
import com.quyt.androidbasekotlin.domain.model.Post

class PostAdapter(private val listener: OnPostListener) : RecyclerView.Adapter<PostViewHolder>() {
    private val mListPost = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = DataBindingUtil.inflate<ItemPostBinding>(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return PostViewHolder(binding,listener)
    }

    override fun getItemCount(): Int {
        return mListPost.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(mListPost[position])
    }

    fun setListPost(listPost: List<Post>) {
       val diffResult = DiffUtil.calculateDiff(PostDiffUtilsCallback(mListPost,listPost))
        mListPost.clear()
        mListPost.addAll(listPost)
        diffResult.dispatchUpdatesTo(this)
    }
}

class PostViewHolder(private val binding: ItemPostBinding,private val listener: OnPostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.post = post
        binding.llRoot.setOnClickListener {
            listener.onPostClick(post.id)
        }
    }
}

interface OnPostListener {
    fun onPostClick(postId: String)
}
