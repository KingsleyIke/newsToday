package com.kings.newstoday.di

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kings.newstoday.data.ArticleApi
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.main.DefaultMainRepository
import com.kings.newstoday.main.MainRepository
import com.kings.newstoday.utils.ArticleAdapter
import com.kings.newstoday.utils.Constants
import com.kings.newstoday.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
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

//    @Provides
//    fun provideArticleAdapter(context: Context, article: Model)  =  ArticleAdapter(context, article)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}