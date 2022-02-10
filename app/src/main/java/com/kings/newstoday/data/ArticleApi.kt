package com.kings.newstoday.data

import com.kings.newstoday.data.models.Model
import retrofit2.Response
import retrofit2.http.GET

interface ArticleApi {

    @GET("/viewed/{period}.json")
    suspend fun getArticles():Response<Model>
}