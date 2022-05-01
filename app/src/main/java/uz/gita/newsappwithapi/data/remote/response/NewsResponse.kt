package uz.gita.newsappwithapi.data.remote.response

import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.data.local.entity.NewsEntity

sealed class NewsResponse {
    data class MainResponse(
        val total: Int,
        val category: String,
        val articles: List<ArticlesData>
    )

    data class ArticlesData(
        val image: String,
        val read_more: String,
        val author: String,
        val description: String,
        val inshorts_link: String,
        val title: String,
        val timestamp: String
    )
}

fun NewsResponse.ArticlesData.toNewsEntity(category: String): NewsEntity = NewsEntity(
    this.image,
    this.read_more,
    this.author,
    this.description,
    this.inshorts_link,
    this.title,
    this.timestamp,
    category
)

fun NewsResponse.ArticlesData.toNewsData() : NewsData = NewsData(
    this.image,
    this.read_more,
    this.author,
    this.description,
    this.title
)
