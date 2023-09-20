package com.example.testnatife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnatife.databinding.GifItemBinding

class GifRVAdapter(
    var items: MutableList<DataResponse> = mutableListOf(),
    var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<GifRVAdapter.SuperheroRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroRecyclerViewHolder {
        return SuperheroRecyclerViewHolder(
            GifItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroRecyclerViewHolder, position: Int) {

        Glide.with(holder.gifImage)
            .load(items[position].data[position].images.original.url)
//            .load(items[position].data.get(position).images.original.url)
            .into(holder.gifImage)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(items[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: DataResponse)
    }

    inner class SuperheroRecyclerViewHolder(binding: GifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val gifImage = binding.imageGif
    }
}