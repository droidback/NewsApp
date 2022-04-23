package uz.gita.newsappwithapi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappwithapi.data.remote.response.NewsResponse

interface NewsRepository {
    fun getNewsByCategory(category : String) : Flow<Result<List<NewsResponse.ArticlesData>>>
}