package com.quyt.androidbasekotlin.presentation.ui.post

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quyt.androidbasekotlin.R
import com.quyt.androidbasekotlin.databinding.FragmentListPostBinding
import com.quyt.androidbasekotlin.presentation.adapter.OnPostListener
import com.quyt.androidbasekotlin.presentation.adapter.PostAdapter
import com.quyt.androidbasekotlin.presentation.base.BaseBindingFragment
import com.quyt.androidbasekotlin.utils.view.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPostFragment : BaseBindingFragment<FragmentListPostBinding,ListPostViewModel>() {

    override fun layoutId(): Int = R.layout.fragment_list_post

    override val viewModel: ListPostViewModel by viewModels()

    private lateinit var mPostAdapter: PostAdapter

    override fun setupView() {
        mPostAdapter = PostAdapter(object : OnPostListener {
            override fun onPostClick(postId: String) {
              findNavController().navigate(
                  ListPostFragmentDirections.actionListPostFragmentToPostDetailFragment(postId)
              )
            }
        })
        binding.rvPost.adapter = mPostAdapter
        binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getListPost()

        viewModel.uiState().observe(viewLifecycleOwner) { state->
            when (state) {
                is ListPostViewModel.ListPostState.Loading -> {
                    LoadingDialog.showLoading(requireContext())
                }
                is ListPostViewModel.ListPostState.Success -> {
                    LoadingDialog.hideLoading()
                    mPostAdapter.setListPost(state.data)
                }
                is ListPostViewModel.ListPostState.Error -> {
                    LoadingDialog.hideLoading()
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}