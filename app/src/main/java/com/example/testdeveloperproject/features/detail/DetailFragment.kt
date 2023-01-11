package com.example.testdeveloperproject.features.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.testdeveloperproject.R
import com.example.testdeveloperproject.common.ui.base.BaseFragment
import com.example.testdeveloperproject.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    private val detailArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailVM by viewModel()
    private lateinit var detailAdapter: DetailGifAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initViews()

        observerListGifs()
        observerPosition()
    }

    private fun getData() {
        viewModel.getData(detailArgs.listGifs)
    }

    private fun initViews() {
        DetailGifAdapter(requireContext()).apply {
            detailAdapter = this
            onSelectedGif = { gif ->
                Glide.with(requireContext()).load(gif.images.original?.url)
                    .into(binding.detailGifImgV)
            }
        }
        binding.detailGifRv.apply {
            this.adapter = detailAdapter
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observerListGifs() {
        viewModel.gifs.observe(viewLifecycleOwner) { gifs ->
            detailAdapter.items = gifs
        }
    }

    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    private fun observerPosition() {
        viewModel.position.observe(viewLifecycleOwner) { position ->
            binding.detailGifRv.layoutManager?.scrollToPosition(position)
            detailAdapter.notifyDataSetChanged()
            Glide.with(requireContext())
                .load(viewModel.gifs.value?.get(position)?.images?.original?.url)
                .into(binding.detailGifImgV)
        }
    }
}