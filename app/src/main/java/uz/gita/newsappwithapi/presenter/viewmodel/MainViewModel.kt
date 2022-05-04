package uz.gita.newsappwithapi.presenter.viewmodel

import androidx.lifecycle.LiveData
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.data.remote.response.NewsResponse

interface MainViewModel {
    val openDrawerLiveData: LiveData<Unit>
    val closeDrawerLiveData: LiveData<Unit>
    val newsLiveData: LiveData<List<NewsData>>
    val errorLiveData: LiveData<String>
    val categoryTitleLiveData: LiveData<String>
    val openWebInfoScreenLiveData: LiveData<NewsData>
    val emptyResultLiveData: LiveData<Unit>

    fun openDrawer()
    fun load(category: String)
    fun swipeRefresh()
    fun openWebInfoScreen(data: NewsData)
}