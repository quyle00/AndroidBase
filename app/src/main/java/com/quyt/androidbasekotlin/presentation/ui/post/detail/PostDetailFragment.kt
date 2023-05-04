package com.quyt.androidbasekotlin.presentation.ui.post.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.FragmentPostDetailBinding
import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.presentation.base.BaseBindingFragment
import com.quyt.androidbasekotlin.utils.view.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : BaseBindingFragment<FragmentPostDetailBinding, PostDetailViewModel>() {

    private val args : PostDetailFragmentArgs by navArgs()
    override fun layoutId(): Int = R.layout.fragment_post_detail
    override val viewModel: PostDetailViewModel by viewModels()

    override fun setupView() {
      viewModel.getPostDetail(args.postId)
        viewModel.uiState().observe(viewLifecycleOwner) { state->
            when (state) {
                is PostDetailViewModel.PostDetailState.Loading -> {
                    LoadingDialog.showLoading(requireContext())
                }
                is PostDetailViewModel.PostDetailState.Success -> {
                    LoadingDialog.hideLoading()
                    initView(state.data)
                }
                is PostDetailViewModel.PostDetailState.Error -> {
                    LoadingDialog.hideLoading()
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    private fun initView(post : Post) {
        Glide.with(requireContext()).load(post.image).into(binding.ivImage)
        binding.tvTitle.text = post.text
        val tagStr = post.tags?.joinToString { it }
        binding.tvTag.text = tagStr
    }

}