package com.kings.newstoday.di

import android.content.Context
import com.kings.newstoday.data.ArticleApi
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.main.DefaultMainRepository
import com.kings.newstoday.main.MainRepository
import com.kings.newstoday.utils.ArticleAdapter
import com.kings.newstoday.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideArticleApi(): ArticleApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_ERL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ArticleApi::class.java)

    @Singleton
    @Provides
    fun provideMainRepository(api: ArticleApi): MainRepository = DefaultMainRepository(api)

    @Provides
    fun provideArticleAdapter(context: Context, article: Model)  =  ArticleAdapter(context, article)
}