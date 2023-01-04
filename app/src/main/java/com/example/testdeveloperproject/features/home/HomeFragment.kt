package com.example.testdeveloperproject.features.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.ui.base.BaseFragment
import com.example.testdeveloperproject.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeVM>()
    private lateinit var gifsAdapter: GifsAdapter
    private var offset = 10

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGifsList()

        initAdapter()
        initViews()

        observerGifs()
        observerIsEmptyGifsList()
    }

    private fun observerIsEmptyGifsList() {
        viewModel.isEmptyGifsList.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                binding.homeGifsRV.visibility = View.GONE
                gifsAdapter.items.clear()
            }
        }
    }

    private fun initAdapter() {
        GifsAdapter(requireContext()).apply {
            gifsAdapter = this
            onItemClick = {
                //TODO add pass to Detail Fragment
            }
        }
    }

    private fun initViews() {
        binding.homeGifsRV.apply {
            adapter = gifsAdapter
            this.layoutManager = GridLayoutManager(requireContext(), 2)
        }
        binding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                offset * 2
                viewModel.getGifsList(offset)
            }
        })
        binding.homeEnterGifNameEd.doOnTextChanged { text, _, _, _ ->
            viewModel.findGifByName(text)
        }
    }

    private fun observerGifs() {
        viewModel.gifs.observe(viewLifecycleOwner) { gifs ->
            binding.homeGifsRV.visibility = View.VISIBLE
            gifsAdapter.addGifs(gifs?.data)
        }
    }
}