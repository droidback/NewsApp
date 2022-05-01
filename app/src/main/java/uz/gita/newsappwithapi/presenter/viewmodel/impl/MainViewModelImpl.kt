package uz.gita.newsappwithapi.presenter.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.data.remote.response.NewsResponse
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import uz.gita.newsappwithapi.presenter.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val repository: NewsRepository) : ViewModel(), MainViewModel {
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()
    override val newsLiveData = MutableLiveData<List<NewsData>>()
    override val errorLiveData = MutableLiveData<String>()
    override val categoryTitleLiveData = MutableLiveData<String>()
    override val openWebInfoScreenLiveData= MutableLiveData<NewsData>()

    init {
        load("all")
    }

    override fun load(category: String) {
        closeDrawer()
        categoryTitleLiveData.value = category.replaceFirstChar { it.uppercase() }
        repository.getNewsByCategory(category).onEach {
            it.onSuccess { newsLiveData.value = it }
            it.onFailure { errorLiveData.value = it.message }
        }.launchIn(viewModelScope)

    }

    override fun swipeRefresh() {
    }

    override fun openWebInfoScreen(data: NewsData) {
        openWebInfoScreenLiveData.value = data
    }

    override fun openDrawer() { openDrawerLiveData.value = Unit }

    private fun closeDrawer() { closeDrawerLiveData.value = Unit }
}