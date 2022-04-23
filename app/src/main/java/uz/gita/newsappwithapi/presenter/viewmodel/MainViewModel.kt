package uz.gita.newsappwithapi.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newsappwithapi.data.remote.response.NewsResponse

interface MainViewModel {
    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>
    val newsLiveData: LiveData<List<NewsResponse.ArticlesData>>
    val errorLiveData: LiveData<String>
    val categoryTitleLiveData: LiveData<String>
    val openWebInfoScreenLiveData: LiveData<NewsResponse.ArticlesData>

    fun openDrawer()
    fun loadNewsByCategory(category: String)
    fun swipeRefresh()
    fun openWebInfoScreen(data: NewsResponse.ArticlesData)
}