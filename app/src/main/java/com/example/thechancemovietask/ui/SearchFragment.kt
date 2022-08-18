package com.example.thechancemovietask.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.thechancemovietask.databinding.FragmentHomeBinding
import com.example.thechancemovietask.databinding.FragmentSearchBinding
import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.network.MovieService
import com.example.thechancemovietask.repository.MoviesRepositoryImpl
import com.example.thechancemovietask.util.DataMoviesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate
) {

//    private val moviesService = MovieService()
//    private val moviesRepositories = MoviesRepositoryImpl(moviesService)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

//    private suspend fun getPopularMovies() {
//        moviesRepositories.getMoviesByMovieKeyword().collect {
//            dataState(it, 3)
//        }
//    }
//
//    private fun dataState(state: DataMoviesState<MoviesTotalResults>, whichDataCameFrom: Int) {
//        when (state) {
//            is DataMoviesState.Error -> Log.d("HGHGHGHG", "FFFFFFFFFFFFaile$whichDataCameFrom")
//            is DataMoviesState.Loading -> Log.d("HGHGHGHG", "LOOOOODing$whichDataCameFrom")
//            is DataMoviesState.Success -> {
//                whichRecyclerWillGetTheData(state.data!!.results, whichDataCameFrom)
//            }
//            else -> {}
//        }
//    }
//
//    private fun getSearchKeyword(){
//    }

}