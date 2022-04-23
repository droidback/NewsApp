package uz.gita.newsappwithapi.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappwithapi.data.remote.response.NewsResponse

interface NewsApi {

    @GET("news")
    suspend fun getNewsByCategory(@Query("category") category: String): Response<NewsResponse.MainResponse>
}