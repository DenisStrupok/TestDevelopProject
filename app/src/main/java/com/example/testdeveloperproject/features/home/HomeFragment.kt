package com.example.testdeveloperproject.features.home

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.ui.base.BaseFragment
import com.example.testdeveloperproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews() {

    }
}