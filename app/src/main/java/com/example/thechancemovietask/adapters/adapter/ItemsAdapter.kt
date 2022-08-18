package com.example.thechancemovietask.adapters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.thechancemovietask.adapters.viewHolders.ItemsViewHolder
import com.example.thechancemovietask.databinding.LayoutMovieRecyclerItemBinding
import com.example.thechancemovietask.model.Movies

class ItemsAdapter : ListAdapter<Movies,ItemsViewHolder>(MovieComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val binding = LayoutMovieRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val movieItems = getItem(position)
        holder.bind(movieItems)
    }

}