package com.example.thechancemovietask.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thechancemovietask.adapters.adapter.ItemsAdapter
import com.example.thechancemovietask.databinding.FragmentDetailsBinding
import com.example.thechancemovietask.databinding.FragmentHomeBinding
import com.example.thechancemovietask.model.Movies
import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.network.MovieService
import com.example.thechancemovietask.repository.MoviesRepositoryImpl
import com.example.thechancemovietask.util.Constants
import com.example.thechancemovietask.util.Constants.MOVIE_IMAGE_URL
import com.example.thechancemovietask.util.DataMoviesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(
    FragmentDetailsBinding::inflate
){

    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var moviesSimilarItemAdapter: ItemsAdapter

    private val moviesService = MovieService()
    private val moviesRepositories = MoviesRepositoryImpl(moviesService)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesSimilarItemAdapter = ItemsAdapter(
            onMovieItemClick = {}
        )

        lifecycleScope.launch(Dispatchers.IO) {
            getSimilarMovies(args.fromDetailsToHomeSendData.id.toInt())

            withContext(Dispatchers.Main){
                initData()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initData(){
        binding.apply {
            textViewTitleDetails.text = args.fromDetailsToHomeSendData.title
            textViewImdbPreview.text = "Tmdb${args.fromDetailsToHomeSendData.voteAverage}"
            textViewDescription.text = args.fromDetailsToHomeSendData.overview

            Glide.with(requireContext())
                .load("$MOVIE_IMAGE_URL${args.fromDetailsToHomeSendData.backdropPath}")
                .into(imageViewHeaderDetails)

            Glide.with(requireContext())
                .load("$MOVIE_IMAGE_URL${args.fromDetailsToHomeSendData.posterPath}")
                .into(imageViewItemDetails)

            initTrendingRecyclerAdapter()
        }
    }

    private fun dataState(state: DataMoviesState<MoviesTotalResults>) {
        when (state) {
            is DataMoviesState.Error -> Log.d("HGHGHGHG", "FFFFFFFFFFFFaile")
            is DataMoviesState.Loading -> Log.d("HGHGHGHG", "LOOOOODing")
            is DataMoviesState.Success -> {
                Log.d("HGHGHGHG", "Successs")
                onDataHeaderMovieSuccess(state.data!!.results)
            }
        }
    }

        private suspend fun getSimilarMovies(movieId:Int) {
        moviesRepositories.getSimilarMovieByMovieId("$movieId").collect {
            dataState(it)
        }
    }

    private fun initTrendingRecyclerAdapter() {
        binding.apply {
            recyclerViewSimilarMovies.layoutManager =
                LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
            recyclerViewSimilarMovies.adapter = moviesSimilarItemAdapter
        }
    }

    private fun onDataHeaderMovieSuccess(state: List<Movies>) {
        moviesSimilarItemAdapter.submitList(state)
    }

}