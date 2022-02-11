package com.kings.newstoday.main

import android.util.Log
import androidx.lifecycle.LiveData
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
    private val repository: DefaultMainRepository,
) : ViewModel() {

    var articleResponse: Model? = null

    init {
        getArticlesList()
    }

    private val _articleList: MutableLiveData<Resource<Model>> = MutableLiveData()
    val articleList: MutableLiveData<Resource<Model>> = _articleList


    fun getArticlesList() {

        viewModelScope.launch(Dispatchers.IO) {

//            when (val articleResponse = repository.getArticles()) {
//                is Resource.Error -> {
////                    _articleList.value = articleResponse.message
//                    Log.e("Error viewmodel", articleResponse.message!!)
//
//                }
//
//                is Resource.Success -> {
//                    _articleList.postValue(articleResponse.data!!)
//                    Log.i("copyright ", articleResponse.data.copyright)
//                    Log.i("status ", articleResponse.data.status)
//                }
//            }
            try {

            val response = repository.getArticles()

            _articleList.postValue(Resource.Loading())
            _articleList.postValue(handleArticleResponse(response))

            } catch (t: Throwable) {
                when(t) {
                    is IOException -> _articleList.postValue(Resource.Error("Network Failure"))
                    else -> _articleList.postValue(Resource.Error("Conversion Error"))
                }
            }
//            val response = repository.getArticles()
//
//            _articleList.postValue(Resource.Loading())
//            _articleList.postValue(handleArticleResponse(response))
        }
    }

    private fun handleArticleResponse(response: Response<Model>) : Resource<Model> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                    articleResponse = resultResponse

                return Resource.Success(articleResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}