package uz.gita.newsappwithapi.presenter.viewmodel

import androidx.lifecycle.LiveData

interface WebInfoViewModel {
    val backBtnLiveData: LiveData<Unit>
    val urlLiveData: LiveData<String>


    //Action
    fun backMainScreen()
    fun initUrl(url: String)
}