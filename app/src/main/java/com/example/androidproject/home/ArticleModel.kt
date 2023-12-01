package com.example.androidproject.home

data class ArticleModel(
    val sellerId: String,
    val title: String,
    val createdAt: Long,
    val price: String,
    val imageUrl: String,
    var isSold: Boolean = false,
) {
    constructor(): this("", "",0, "", "",false)
}
