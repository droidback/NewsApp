package uz.gita.newsappwithapi.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappwithapi.data.remote.api.NewsApi
import uz.gita.newsappwithapi.data.remote.response.NewsResponse
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi) : NewsRepository {
    override fun getNewsByCategory(category: String) = flow<Result<List<NewsResponse.ArticlesData>>> {
        try {
            val response = api.getNewsByCategory(category)
            when (response.isSuccessful) {
                true -> { response.body()?.let { emit(Result.success(it.articles)) }}
                false -> emit(Result.failure(Exception("Connect error")))
            }
        } catch (e: Exception){ emit(Result.failure(e))}
    }.flowOn(Dispatchers.Default)
}