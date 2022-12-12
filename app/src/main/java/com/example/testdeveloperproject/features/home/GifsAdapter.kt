package com.example.testdeveloperproject.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Gif
import com.example.testdeveloperproject.databinding.ItemGifRvBinding

class GifsAdapter(private val context: Context): RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    var onItemClick : ((Gif) -> Unit)? = null

    var items: List<Gif>?
        set(value) {
            _items = value
        }
        get() = _items

    private var _items: List<Gif>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGifRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it) }

    }

    override fun getItemId(position: Int): Long {
        return items?.get(position)?.id?.hashCode()?.toLong() ?: 0L
    }

    override fun getItemCount(): Int = items?.size ?: 0

    inner class ViewHolder(private val binding: ItemGifRvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Gif){
            Glide.with(context).load(item.images.original?.url).into(binding.itemGifImg)
            binding.itemGifContainer.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }
}