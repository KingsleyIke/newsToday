package com.kings.newstoday.data

import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("/viewed/${Constants.PERIOD}.json")
    suspend fun getArticles(
        @Query(Constants.API_KEY) api_key: String?
    ):Response<Model>


}