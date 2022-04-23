package uz.gita.newsappwithapi.presenter.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.newsappwithapi.data.remote.api.NewsApi
import uz.gita.newsappwithapi.domain.repository.NewsRepository
import uz.gita.newsappwithapi.presenter.viewmodel.WebInfoViewModel
import javax.inject.Inject

@HiltViewModel
class WebInfoViewModelImpl @Inject constructor(private val repository: NewsRepository, private val api: NewsApi) : ViewModel(), WebInfoViewModel {
    override val backBtnLiveData = MutableLiveData<Unit>()
    override val urlLiveData = MutableLiveData<String>()


    override fun backMainScreen() {
        backBtnLiveData.value = Unit
    }

    override fun initUrl(url: String) {
        urlLiveData.value = url
    }

}