package com.quyt.androidbasekotlin.presentation.ui.post.detail

import androidx.lifecycle.viewModelScope
import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.domain.model.Result
import com.quyt.androidbasekotlin.domain.usecase.GetPostDetailUseCase
import com.quyt.androidbasekotlin.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailViewModel @Inject constructor(private val getPostDetailUseCase: GetPostDetailUseCase)
    : BaseViewModel<PostDetailViewModel.PostDetailState>() {

    sealed class PostDetailState {
        object Loading : PostDetailState()
        data class Success(val data: Post) : PostDetailState()
        data class Error(val error: String) : PostDetailState()
    }

    fun getPostDetail(id: String) {
      viewModelScope.launch {
          uiState.postValue(PostDetailState.Loading)
          val result = getPostDetailUseCase(id)
          when (result) {
              is Result.Success -> {
                  uiState.postValue(PostDetailState.Success(result.data))
              }
              is Result.Error -> {
                  uiState.postValue(PostDetailState.Error(result.exception.message ?: "Error"))
              }
          }
      }
    }

}