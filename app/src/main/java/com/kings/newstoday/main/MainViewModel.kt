package com.kings.newstoday.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    init {
        getArticlesList()
    }

    private val _articleList: MutableLiveData<Resource<Model>> = MutableLiveData()
    val articleList: MutableLiveData<Resource<Model>> = _articleList


    fun getArticlesList() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
            val response = repository.getArticles()

            _articleList.postValue(Resource.Loading())
            _articleList.postValue(response)

            } catch (t: Throwable) {
                when(t) {
                    is IOException -> _articleList.postValue(Resource.Error("Network Failure"))
                    else -> _articleList.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

}