package com.kings.newstoday.data.models

data class Model(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)