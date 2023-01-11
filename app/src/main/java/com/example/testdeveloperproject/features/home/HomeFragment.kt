package com.example.testdeveloperproject.features.home

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.body.GifBody
import com.example.testdeveloperproject.common.body.GifsBody
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
            onItemClick = { selectedFifId ->
                actionOpenDetailFragment(selectedFifId)
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
                offset + 10
                if (viewModel.nameGif.value?.isNotEmpty() == true) {
                    viewModel.findGifByName(nameGif = viewModel.nameGif.value.toString(), offset =  offset)
                } else {
                    viewModel.getGifsList(offset)
                }
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

    private fun actionOpenDetailFragment(selectedGifId: String) {
        val gifs = GifsBody()
        viewModel.changeSelectedGif(selectedGifId)
        viewModel.gifs.value?.data?.forEach { gif ->
            val newGif = GifBody(
                id = gif.id,
                url = gif.url,
                images = gif.images,
                isSelectedGif = gif.isSelectedGif
            )
            gifs.add(newGif)
        }

        findNavController().navigate(HomeFragmentDirections.actionOpenDetailFragment(gifs))
    }
}