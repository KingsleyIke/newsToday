package com.kings.newstoday.main

import com.kings.newstoday.data.models.Model
import retrofit2.Response

class TestRepository : MainRepository {

    override suspend fun getArticles(): Response<Model> {
    }
}