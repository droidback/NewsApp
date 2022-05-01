package uz.gita.newsappwithapi.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.data.remote.response.NewsResponse

interface NewsRepository {
    fun getNewsByCategory(category : String) : Flow<Result<List<NewsData>>>
    fun getNewsFromRoom(category: String): Flow<Result<List<NewsData>>>
}