package uz.gita.newsappwithapi.presenter.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappwithapi.R
import uz.gita.newsappwithapi.data.remote.response.NewsResponse
import uz.gita.newsappwithapi.databinding.ScreenMainBinding
import uz.gita.newsappwithapi.presenter.ui.adapter.NewsAdapter
import uz.gita.newsappwithapi.presenter.viewmodel.MainViewModel
import uz.gita.newsappwithapi.presenter.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)

    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter = NewsAdapter()


    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        menuBtn.setOnClickListener { viewModel.openDrawer() }
        adapter.setOnCategoryClickListener {  viewModel.openWebInfoScreen(it)}
        swipeRefresh.setOnRefreshListener {  }
        selectCategory()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.closeDrawerLiveData.observe(this@MainScreen, closeDrawerObserver)
        viewModel.openDrawerLiveData.observe(this@MainScreen, openDrawerObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        viewModel.categoryTitleLiveData.observe(viewLifecycleOwner, categoryTitleObserver)
        viewModel.openWebInfoScreenLiveData.observe(this@MainScreen, openWebInfoScreenObserver)
    }


    private val closeDrawerObserver = Observer<Unit> {
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    private val openDrawerObserver = Observer<Unit> {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
    private val errorObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    private val newsObserver = Observer<List<NewsResponse.ArticlesData>> {
        adapter.submitList(it)
    }
    private val categoryTitleObserver = Observer<String> {
        binding.title.text = it
    }

    private val openWebInfoScreenObserver = Observer<NewsResponse.ArticlesData>{
        findNavController().navigate(MainScreenDirections.actionMainScreenToWebScreen(it.title, it.read_more))
    }


    private fun selectCategory() = with(binding.navigationLayout) {
        allCategory.setOnClickListener { viewModel.loadNewsByCategory("all") }
        nationalCategory.setOnClickListener { viewModel.loadNewsByCategory("national") }
        businessCategory.setOnClickListener { viewModel.loadNewsByCategory("business") }
        sportsCategory.setOnClickListener { viewModel.loadNewsByCategory("sports") }
        worldCategory.setOnClickListener { viewModel.loadNewsByCategory("world") }
        politicsCategory.setOnClickListener { viewModel.loadNewsByCategory("politics") }
        technologyCategory.setOnClickListener { viewModel.loadNewsByCategory("technology") }
        startUpCategory.setOnClickListener { viewModel.loadNewsByCategory("startup") }
        entertainmentCategory.setOnClickListener { viewModel.loadNewsByCategory("entertainment") }
        scienceCategory.setOnClickListener { viewModel.loadNewsByCategory("science") }
        automobileCategory.setOnClickListener { viewModel.loadNewsByCategory("automobile") }
    }

}