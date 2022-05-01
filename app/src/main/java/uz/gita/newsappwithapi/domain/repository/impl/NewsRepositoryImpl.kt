package uz.gita.newsappwithapi.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.data.local.dao.NewsDao
import uz.gita.newsappwithapi.data.local.entity.toNewsData
import uz.gita.newsappwithapi.data.remote.api.NewsApi
import uz.gita.newsappwithapi.data.remote.response.NewsResponse
import uz.gita.newsappwithapi.data.remote.response.toNewsData
import uz.gita.newsappwithapi.data.remote.response.toNewsEntity
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi, private val database: NewsDao) : NewsRepository {
    override fun getNewsByCategory(category: String): Flow<Result<List<NewsData>>> = flow<Result<List<NewsData>>> {
        try {
            val response = api.getNewsByCategory(category)
            when (response.isSuccessful) {
                true -> {
                    response.body()?.let {
                        database.deleteNewsData(category)
                        database.insertAllNews(it.articles.map { data -> data.toNewsEntity(category) })
                        emit(Result.success(it.articles.map {data-> data.toNewsData() }))
                    }
                }
                false -> emit(Result.failure(Exception("Connect error")))
            }
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getNewsFromRoom(category: String): Flow<Result<List<NewsData>>> = flow<Result<List<NewsData>>> {
        emit(Result.success(database.getNewsByCategory(category).map{data -> data.toNewsData()}))
    }.catch {
        emit(Result.failure(Exception(it)))
    }.flowOn(Dispatchers.IO)
}