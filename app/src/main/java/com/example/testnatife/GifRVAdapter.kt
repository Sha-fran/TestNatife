package com.example.testnatife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testnatife.databinding.GifItemBinding

class GifRVAdapter(
    var items: MutableList<Data> = mutableListOf(),
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
//        holder.superheroName.text = String.format("Name: ${items[position].name}")
//        holder.superheroSlug.text = String.format("Slug: ${items[position].slug}")
//        holder.superheroGender.text = String.format("Gender: ${items[position].appearance?.gender}")
//        holder.superheroRace.text = String.format("Race: ${items[position].appearance?.race}")

        Glide.with(holder.superheroImage)
//            .load(items[position].images?.lg)
            .load(items[position].images?.hd?.mp4)
            .into(holder.superheroImage)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(items[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Data)
    }

    inner class SuperheroRecyclerViewHolder(binding: GifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val superheroImage = binding.imageGif
    }
}