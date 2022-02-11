package com.kings.newstoday.main

import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.Resource
import retrofit2.Response

interface MainRepository {

    suspend fun getArticles(): Resource<Model>

}