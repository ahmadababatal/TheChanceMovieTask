package com.example.thechancemovietask.adapters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.thechancemovietask.adapters.viewHolders.HeaderViewHolder
import com.example.thechancemovietask.adapters.viewHolders.ItemsViewHolder
import com.example.thechancemovietask.databinding.LayoutHeaderRecyclerItemBinding
import com.example.thechancemovietask.databinding.LayoutMovieRecyclerItemBinding
import com.example.thechancemovietask.model.Movies

class HeaderAdapter(
    private val onMovieHeaderClick: (Movies) -> Unit
) :
    ListAdapter<Movies, HeaderViewHolder>(MovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val binding = LayoutHeaderRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HeaderViewHolder(binding,
            onHeaderClickItems = { position ->
                val movie = getItem(position)
                if (movie != null) {
                    onMovieHeaderClick(movie)
                }
            }
        )
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        val movieItems = getItem(position)
        holder.bind(movieItems)
    }
}