package com.quyt.androidbasekotlin.presentation.ui.post

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.FragmentListPostBinding
import com.quyt.androidbasekotlin.presentation.adapter.PostAdapter
import com.quyt.androidbasekotlin.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPostFragment : BaseFragment<FragmentListPostBinding,ListPostViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_list_post

    override val viewModel: ListPostViewModel by viewModels()

    private lateinit var mPostAdapter: PostAdapter


    override fun setupView() {
        mPostAdapter = PostAdapter()
        binding.rvPost.adapter = mPostAdapter
        binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getListPost()

        viewModel.uiState().observe(viewLifecycleOwner) { state->
            when (state) {
                is ListPostViewModel.ListPostState.Loading -> {

                }
                is ListPostViewModel.ListPostState.Success -> {
                    mPostAdapter.setListPost(state.data)
                }
                is ListPostViewModel.ListPostState.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}