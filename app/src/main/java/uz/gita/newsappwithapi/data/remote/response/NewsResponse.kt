package uz.gita.newsappwithapi.data.remote.response

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