package com.michael

import com.bumptech.glide.Glide
import com.michael.data.RepositoryImpl
import com.michael.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule2 {

    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()


    @JvmStatic
    @Provides
    @Singleton
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return okHttpClientBuilder
            .addInterceptor(interceptor)
            .build()
    }
}