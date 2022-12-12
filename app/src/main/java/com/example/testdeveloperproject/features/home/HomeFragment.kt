package com.example.testdeveloperproject.features.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.model.Gif
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.ui.base.BaseFragment
import com.example.testdeveloperproject.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModel<HomeVM>()
    private lateinit var gifsAdapter: GifsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGifsList()

        initAdapter()
        initViews()

        observerGifs()
    }

    private fun initAdapter(){
        GifsAdapter(requireContext()).apply {
            gifsAdapter = this
            onItemClick = {
                val test = it
                test.toString()
            }
            setHasStableIds(true)
        }
    }

    private fun initViews() {
        binding.homeGifsRV.apply{
            adapter = gifsAdapter
            this.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun observerGifs(){
        viewModel.gifs.observe(viewLifecycleOwner){ gifs ->
            gifsAdapter.items = gifs.data
            gifsAdapter.notifyDataSetChanged()
        }
    }
}