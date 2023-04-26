package com.quyt.androidbasekotlin.di.module

import android.content.Context
import androidx.room.Room
import com.quyt.androidbasekotlin.data.datasource.local.database.AppDatabase
import com.quyt.androidbasekotlin.data.datasource.remote.interceptor.HeaderInterceptor
import com.quyt.androidbasekotlin.data.datasource.remote.service.PostApiService
import com.quyt.androidbasekotlin.domain.repository.PostRepository
import com.quyt.androidbasekotlin.domain.usecase.GetListPostUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my_app_database"
        ).build()
    }

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor) = if (BuildConfig.DEBUG) {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(headerInterceptor)
//            .build()
//    } else {
//        OkHttpClient.Builder()
//            .addInterceptor(headerInterceptor)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(headerInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://dummyapi.io/data/v1/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

    @Provides
    @Singleton
    fun providePostApiServices(retrofit: Retrofit): PostApiService = retrofit.create(PostApiService::class.java)

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: PostRepository): GetListPostUseCase {
        return GetListPostUseCase(repository)
    }

}