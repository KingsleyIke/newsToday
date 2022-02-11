package com.kings.newstoday.main

import android.view.Display
import androidx.lifecycle.MutableLiveData
import com.kings.newstoday.utils.Resource
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TestViewModel {

    private lateinit var viewModel: MainViewModel
    lateinit var repository: TestRepository

    var model: MutableLiveData<Resource<Display.Mode>> = MutableLiveData()
    @Before
    suspend fun setUp() {
        repository = TestRepository()
        viewModel = MainViewModel(TestRepository())
        repository.getArticles()
    }

    @Test
    fun `getArticles return all articles response`() = runBlocking {
        model.postValue(Resource.Loading())
        TestCase.assertTrue(repository.getArticles().data?.results!!.isNotEmpty())
    }

    @Test
    fun `getArticles copyright all returns copyright`() = runBlocking {
        model.postValue(Resource.Loading())
        TestCase.assertTrue(repository.getArticles().data?.copyright!!.isNotEmpty())
    }

    @Test
    fun `getArticles value status return  title`() = runBlocking {
        model.postValue(Resource.Loading())
        TestCase.assertTrue(repository.getArticles().data?.status!!.isNotEmpty())
    }
}