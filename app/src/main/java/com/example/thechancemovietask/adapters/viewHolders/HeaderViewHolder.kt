package com.example.thechancemovietask.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thechancemovietask.databinding.LayoutHeaderRecyclerItemBinding
import com.example.thechancemovietask.model.Movies
import com.example.thechancemovietask.util.Constants.MOVIE_IMAGE_URL

class HeaderViewHolder(
    private val binding: LayoutHeaderRecyclerItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movies){
        binding.apply {
            binding.textItemTitle.text = movie.title
            Glide.with(itemView)
                .load(MOVIE_IMAGE_URL+movie.backdropPath)
                .into(binding.imageViewHeader)
        }
    }

}