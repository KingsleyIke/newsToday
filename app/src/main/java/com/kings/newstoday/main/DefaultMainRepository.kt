package com.kings.newstoday.main

import com.kings.newstoday.data.ArticleApi
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.Constants
import com.kings.newstoday.utils.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: ArticleApi
) : MainRepository {
    override suspend fun getArticles(): Resource<Model> {

        return try {
            val response = api.getArticles(Constants.API_KEY)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }

}