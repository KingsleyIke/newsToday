package com.kings.newstoday.main

import com.kings.newstoday.data.models.Media
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.data.models.Result
import com.kings.newstoday.utils.Resource
import retrofit2.Response

class TestRepository : MainRepository {


    private var shouldRetrunNetworkError = false

    val result1 = Result("Sample Text","Sample Text",2,"Sample Text", "Sample Text",listOf(""),2,listOf(""),2,
        listOf(),"", listOf(""),listOf(""),"",
        "Sample Text","Sample Text","2","Sample Text", "Sample Text","Sample Text","Sample Text","Sample Text")

    val result2 = Result("Sample Text","Sample Text",2,"Sample Text", "Sample Text",listOf(""),2,listOf(""),2,
        listOf(),"", listOf(""),listOf(""),"",
        "Sample Text","Sample Text","2","Sample Text", "Sample Text","Sample Text","Sample Text","Sample Text")

    val articleList = mutableListOf<Result>()

    val model = Model("",2, articleList, "")

    override suspend fun getArticles(): Resource<Model> {

        return if(shouldRetrunNetworkError) {
            Resource.Error("Error")
        } else {
            Resource.Success(model)
        }
    }
}