package com.example.testdeveloperproject.features.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testdeveloperproject.common.body.GifBody
import com.example.testdeveloperproject.databinding.ItemDetailGifBinding

class DetailGifAdapter(private val context: Context): RecyclerView.Adapter<DetailGifAdapter.DetailViewHolder>() {

    var onSelectedGif: ((GifBody) -> Unit)? = null

    var items: List<GifBody>?
        set(value) {
            _items = value
        }
        get() = _items


    private var _items: List<GifBody>? = null
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
           ItemDetailGifBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

   inner class DetailViewHolder(private val binding: ItemDetailGifBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: GifBody){
            Glide.with(context).load(item.images.original?.url).into(binding.itemDetailGifImgV)
            binding.detailItemContainer.setOnClickListener {
                onSelectedGif?.invoke(item)
            }
        }
    }

}