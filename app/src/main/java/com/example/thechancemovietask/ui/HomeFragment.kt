package com.example.thechancemovietask.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.thechancemovietask.R
import com.example.thechancemovietask.databinding.FragmentHomeBinding
import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.network.MovieService
import com.example.thechancemovietask.repository.MoviesRepositoryImpl
import com.example.thechancemovietask.util.DataMoviesState

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val moviesService = MovieService()
    private val moviesRepositories = MoviesRepositoryImpl(moviesService)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    private suspend fun getTrendingMovies() {
        moviesRepositories.getTrendingMovies().collect {
            dataState(it)
        }
    }

//    private suspend fun getSearchingMovies() {
//        val moviesService = MovieService()
//        val moviesRepositories = MoviesRepositoryImpl(moviesService)
//        moviesRepositories.getMoviesByMovieKeyword("prey").collect {
//            dataState(it)
//        }
//    }

    private suspend fun getPopularMovies() {
        val moviesService = MovieService()
        val moviesRepositories = MoviesRepositoryImpl(moviesService)
        moviesRepositories.getPopularMovies().collect {
            dataState(it)
        }
    }

//    private suspend fun getSimilarMovies() {
//        val moviesService = MovieService()
//        val moviesRepositories = MoviesRepositoryImpl(moviesService)
//        moviesRepositories.getSimilarMovieByMovieId("1242").collect {
//            dataState(it)
//        }
//    }

    private fun dataState(state: DataMoviesState<MoviesTotalResults>) {
        when (state) {
            is DataMoviesState.Error -> Log.d("HGHGHGHG", "FFFFFFFFFFFFaile")
            DataMoviesState.Loading -> Log.d("HGHGHGHG", "LOOOOODing")
            is DataMoviesState.Success -> Log.d("HGHGHGHG", "SSSSSSSUccess")
            else -> {}
        }
    }

    fun onDataMovieSuccess(binding:FragmentHomeBinding){




    }

}