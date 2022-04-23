package uz.gita.newsappwithapi.presenter.ui.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.newsappwithapi.R
import uz.gita.newsappwithapi.databinding.ScreenWebInfoBinding
import uz.gita.newsappwithapi.presenter.viewmodel.WebInfoViewModel
import uz.gita.newsappwithapi.presenter.viewmodel.impl.WebInfoViewModelImpl

@AndroidEntryPoint
class WebInfoScreen:Fragment(R.layout.screen_web_info) {
    private val viewBinding: ScreenWebInfoBinding by viewBinding(ScreenWebInfoBinding::bind)
    private val viewModel: WebInfoViewModel by viewModels<WebInfoViewModelImpl>()
    private val args: WebInfoScreenArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.backBtnLiveData.observe(viewLifecycleOwner, backObserver)
        viewModel.urlLiveData.observe(viewLifecycleOwner, urlObserver)

        args.readMore?.let { viewModel.initUrl(it) }
        viewBinding.backBtn.setOnClickListener { viewModel.backMainScreen() }
    }


    private val backObserver = Observer<Unit>{
        findNavController().popBackStack()
    }

    private val urlObserver = Observer<String> {
        viewBinding.apply {
            webScreen.loadUrl(it)
            titleWebScreen.text = args.title.trim()
        }
    }
}