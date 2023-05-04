package com.quyt.androidbasekotlin.presentation.ui.post

import androidx.lifecycle.viewModelScope
import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.domain.model.Result
import com.quyt.androidbasekotlin.domain.usecase.GetListPostUseCase
import com.quyt.androidbasekotlin.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListPostViewModel @Inject constructor(private val getPostsUseCase: GetListPostUseCase)
    : BaseViewModel<ListPostViewModel.ListPostState>() {

    sealed class ListPostState {
        object Loading : ListPostState()
        data class Success(val data: List<Post>) : ListPostState()
        data class Error(val error: String) : ListPostState()
    }

    private val mCurrentPage = 0

    fun getListPost() {
        viewModelScope.launch {
            uiState.postValue(ListPostState.Loading)
            val result = getPostsUseCase(mCurrentPage, 20)
            when (result) {
                is Result.Success -> {
                    uiState.postValue(ListPostState.Success(result.data))
                }

                is Result.Error -> {
                    uiState.postValue(ListPostState.Error(result.exception.message ?: "Error"))
                }
            }
        }
    }
}