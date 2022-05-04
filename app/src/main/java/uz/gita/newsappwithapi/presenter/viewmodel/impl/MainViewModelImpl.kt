package uz.gita.newsappwithapi.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.newsappuseroom.data.model.NewsData
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import uz.gita.newsappwithapi.presenter.viewmodel.MainViewModel
import uz.gita.newsappwithapi.utils.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(private val repository: NewsRepository) : ViewModel(), MainViewModel {
    override val openDrawerLiveData = MutableLiveData<Unit>()
    override val closeDrawerLiveData = MutableLiveData<Unit>()
    override val newsLiveData = MutableLiveData<List<NewsData>>()
    override val errorLiveData = MutableLiveData<String>()
    override val categoryTitleLiveData = MutableLiveData<String>()
    override val openWebInfoScreenLiveData = MutableLiveData<NewsData>()
    override val emptyResultLiveData = MutableLiveData<Unit>()


    init {
        load("all")
    }

    override fun load(category: String) {
        when (isConnected()) {
            true -> loadNetwork(category)
            false -> loadLocal(category)
        }

    }

    private fun loadNetwork(category: String) {
        closeDrawer()
        categoryTitleLiveData.value = category.replaceFirstChar { it.uppercase() }
        repository.getNewsByCategory(category).onEach {
            it.onSuccess { newsLiveData.value = it }
            it.onFailure { errorLiveData.value = it.message }
        }.launchIn(viewModelScope)
    }

    private fun loadLocal(category: String) {
        closeDrawer()
        categoryTitleLiveData.value = category.replaceFirstChar { it.uppercase() }
        errorLiveData.value = "Internet ishlamayabti"

        repository.getNewsFromRoom(category).onEach {
            it.onSuccess { data ->
                when (data.isEmpty()) {
                    true -> emptyResultLiveData.value = Unit
                    false -> newsLiveData.value = data
                }
            }

            it.onFailure { throwable ->
                errorLiveData.value = throwable.message
            }
        }.launchIn(viewModelScope)

    }

    override fun swipeRefresh() {
    }

    override fun openWebInfoScreen(data: NewsData) {
        openWebInfoScreenLiveData.value = data
    }

    override fun openDrawer() {
        openDrawerLiveData.value = Unit
    }

    private fun closeDrawer() {
        closeDrawerLiveData.value = Unit
    }
}