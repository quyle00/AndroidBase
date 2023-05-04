package com.quyt.androidbasekotlin.data.repository

import com.quyt.androidbasekotlin.data.datasource.local.PostLocalDatasource
import com.quyt.androidbasekotlin.data.datasource.remote.PostRemoteDatasource
import com.quyt.androidbasekotlin.data.datasource.remote.model.toPostList
import com.quyt.androidbasekotlin.domain.model.Post
import com.quyt.androidbasekotlin.domain.model.Result
import com.quyt.androidbasekotlin.domain.repository.PostRepository
import com.quyt.androidbasekotlin.utils.network.NetworkChecker
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val remote: PostRemoteDatasource,
    private val local: PostLocalDatasource,
    private val networkChecker: NetworkChecker
) : PostRepository {

    override suspend fun getPosts(page: Int, limit: Int): Result<List<Post>> {
        return try {
            if (networkChecker.isNetworkConnected()) {
                val postResponse = remote.getPosts(page = page, limit = limit)
                val postList = postResponse.toPostList()
                local.insertPost(postList)
                Result.Success(postList)
            } else {
                val localPost = local.getAllPost()
                if (localPost.isEmpty()) {
                    Result.Error(Exception("No internet. Local data is empty"))
                } else {
                    Result.Success(localPost)
                }
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPostDetail(id: String): Result<Post> {
        return try {
            if (networkChecker.isNetworkConnected()) {
                // connected to internet
                val post = remote.getPostDetail(id)
                Result.Success(post)
            } else {
                // not connected
                Result.Error(Exception("No internet"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
