package com.quyt.androidbasekotlin.di.module

import android.content.Context
import com.quyt.androidbasekotlin.data.datasource.local.PostLocalDatasource
import com.quyt.androidbasekotlin.data.datasource.local.PostLocalDatasourceImpl
import com.quyt.androidbasekotlin.data.datasource.local.database.AppDatabase
import com.quyt.androidbasekotlin.data.datasource.remote.PostRemoteDatasource
import com.quyt.androidbasekotlin.data.datasource.remote.PostRemoteDatasourceImpl
import com.quyt.androidbasekotlin.data.datasource.remote.service.PostApiService
import com.quyt.androidbasekotlin.data.repository.PostRepositoryImpl
import com.quyt.androidbasekotlin.domain.repository.PostRepository
import com.quyt.androidbasekotlin.utils.network.NetworkChecker
import com.quyt.androidbasekotlin.utils.network.NetworkCheckerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePostRemoteDatasource(services: PostApiService): PostRemoteDatasource {
        return PostRemoteDatasourceImpl(services)
    }

    @Provides
    @Singleton
    fun provideNewsLocalDatasource(appDatabase: AppDatabase): PostLocalDatasource {
        return PostLocalDatasourceImpl(appDatabase)
    }


    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext ctx: Context): NetworkChecker {
        return NetworkCheckerImpl(ctx)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        remote: PostRemoteDatasource,
        local: PostLocalDatasource,
        networkCheck: NetworkChecker
    ): PostRepository {
        return PostRepositoryImpl(remote,local,networkCheck)
    }


}