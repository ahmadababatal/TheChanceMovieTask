package com.example.thechancemovietask.adapters.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thechancemovietask.databinding.LayoutMovieRecyclerItemBinding
import com.example.thechancemovietask.model.Movies
import com.example.thechancemovietask.util.Constants.MOVIE_IMAGE_URL

class ItemsViewHolder(
    private val binding: LayoutMovieRecyclerItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie:Movies){
        binding.apply {
            binding.textViewItemTitle.text = movie.title

            Glide.with(itemView)
                .load(MOVIE_IMAGE_URL+movie.posterPath)
                .into(binding.imageViewItem)
        }
    }
}