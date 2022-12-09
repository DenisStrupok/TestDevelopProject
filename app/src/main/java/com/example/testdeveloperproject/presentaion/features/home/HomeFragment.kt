package com.example.testdeveloperproject.presentaion.features.home

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.presentaion.common.ui.base.BaseFragment
import com.example.testdeveloperproject.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGifsList()
        initViews()

        observerGifs()
    }

    private fun initViews() {

    }

    private fun observerGifs(){

    }
}