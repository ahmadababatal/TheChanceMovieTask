package com.example.thechancemovietask.adapters.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.thechancemovietask.model.Movies

class MovieComparator: DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies) =
        oldItem == newItem

}