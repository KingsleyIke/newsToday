package com.kings.newstoday.data

import com.kings.newstoday.data.models.Model
import com.kings.newstoday.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    @GET("/svc/mostpopular/v2//viewed/7.json?api-key=A0XSjiAU6s8JQhaaK7GIUdHrD87Yiqka")
    suspend fun getArticles():Response<Model>


}