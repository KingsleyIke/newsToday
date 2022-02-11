package com.kings.newstoday.main

import com.kings.newstoday.data.models.Media
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.data.models.Result
import com.kings.newstoday.utils.Resource
import retrofit2.Response

class TestRepository : MainRepository {


    private var shouldRetrunNetworkError = false

    val result1 = Result("","",2,"", "",listOf(""),2,listOf(""),2,
        listOf(),"", listOf(""),listOf(""),"",
        "","","2","", "","","","")

    val result2 = Result("","",2,"", "",listOf(""),2,listOf(""),2,
    listOf(),"", listOf(""),listOf(""),"",
    "","","2","", "","","","")

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