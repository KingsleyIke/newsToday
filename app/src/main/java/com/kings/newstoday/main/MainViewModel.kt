package com.kings.newstoday.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.DispatcherProvider
import com.kings.newstoday.utils.Resource
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class ArticleRetrievalEvent {
        class Success(val resultText: Model): ArticleRetrievalEvent()
        class Failure(val errorText: String): ArticleRetrievalEvent()
        object Loading : ArticleRetrievalEvent()
        object Empty : ArticleRetrievalEvent()
    }

    private val _articleList = MutableLiveData<ArticleRetrievalEvent>(ArticleRetrievalEvent.Empty)
    val articleList: LiveData<ArticleRetrievalEvent> = _articleList


    fun getArticlesList() {

        viewModelScope.launch (dispatchers.io) {
            _articleList.value = ArticleRetrievalEvent.Loading

            when (val articleResponse = repository.getArticles()) {
                is Resource.Error -> _articleList.value = ArticleRetrievalEvent.Failure(articleResponse.message!!)

                is Resource.Success -> {
                    _articleList.value = ArticleRetrievalEvent.Success(articleResponse.data!!)
                    Log.i("copyright ", (_articleList.value as ArticleRetrievalEvent.Success).resultText.copyright)
                    Log.i("staus ", (_articleList.value as ArticleRetrievalEvent.Success).resultText.status)
                }
            }
        }
    }
}