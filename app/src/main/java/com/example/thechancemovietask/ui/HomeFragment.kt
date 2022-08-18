package com.example.thechancemovietask.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thechancemovietask.R
import com.example.thechancemovietask.adapters.adapter.HeaderAdapter
import com.example.thechancemovietask.adapters.adapter.ItemsAdapter
import com.example.thechancemovietask.databinding.FragmentHomeBinding
import com.example.thechancemovietask.model.Movies
import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.network.MovieService
import com.example.thechancemovietask.repository.MoviesRepositoryImpl
import com.example.thechancemovietask.util.DataMoviesState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val moviesService = MovieService()
    private val moviesRepositories = MoviesRepositoryImpl(moviesService)

    private lateinit var moviesHeaderAdapter: HeaderAdapter
    private lateinit var moviesTrendingItemAdapter: ItemsAdapter
    private lateinit var moviesPopularItemAdapter: ItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesHeaderAdapter = HeaderAdapter(
            onMovieHeaderClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
        )

        moviesTrendingItemAdapter = ItemsAdapter(
            onMovieItemClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
        )

        moviesPopularItemAdapter = ItemsAdapter(
            onMovieItemClick = {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }
        )

        lifecycleScope.launch(Dispatchers.IO) {

            getTrendingMovies()
            getPopularMovies()
            getHeaderMovies()

            withContext(Dispatchers.Main) {
                initHeaderRecyclerAdapter()
                initTrendingRecyclerAdapter()
                initPopularRecyclerAdapter()
            }
        }

    }

    private suspend fun getHeaderMovies() {
        moviesRepositories.getPopularMovies().collect {
            dataState(it, 1)
        }
    }

    private suspend fun getTrendingMovies() {
        moviesRepositories.getTrendingMovies().collect {
            dataState(it, 2)
        }
    }

    private suspend fun getPopularMovies() {
        moviesRepositories.getPopularMovies().collect {
            dataState(it, 3)
        }
    }

    private fun dataState(state: DataMoviesState<MoviesTotalResults>, whichDataCameFrom: Int) {
        when (state) {
            is DataMoviesState.Error -> { displayFailureState() }
            is DataMoviesState.Loading -> { displayLoadingState() }
            is DataMoviesState.Success -> {
                whichRecyclerWillGetTheData(state.data!!.results, whichDataCameFrom)
            }
        }
    }

    private fun whichRecyclerWillGetTheData(state: List<Movies>, _whichDataCameFrom: Int) {
        when (_whichDataCameFrom) {
            1 -> onDataHeaderMovieSuccess(state)
            2 -> onDataTrendingMovieSuccess(state)
            3 -> onDataPopularMovieSuccess(state)
        }
    }

    private fun onDataHeaderMovieSuccess(state: List<Movies>) {
        moviesHeaderAdapter.submitList(state)
    }

    private fun onDataTrendingMovieSuccess(state: List<Movies>) {
        moviesTrendingItemAdapter.submitList(state)
    }

    private fun onDataPopularMovieSuccess(state: List<Movies>) {
        moviesPopularItemAdapter.submitList(state)
    }

    private fun initHeaderRecyclerAdapter() {
        binding.apply {
            recyclerHeaderItems.layoutManager =
                LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
            recyclerHeaderItems.adapter = moviesHeaderAdapter
            binding.group.visibility = View.VISIBLE
            binding.animationLoding.visibility = View.INVISIBLE
        }
    }

    private fun initTrendingRecyclerAdapter() {
        binding.apply {
            recyclerTrendingItems.layoutManager =
                LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
            recyclerTrendingItems.adapter = moviesTrendingItemAdapter
            binding.group.visibility = View.VISIBLE
            binding.animationLoding.visibility = View.INVISIBLE
        }
    }

    private fun initPopularRecyclerAdapter() {
        binding.apply {
            recyclerPopularItems.layoutManager =
                LinearLayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false
                )
            recyclerPopularItems.adapter = moviesPopularItemAdapter
            binding.group.visibility = View.VISIBLE
            binding.animationLoding.visibility = View.INVISIBLE
        }
    }

    private fun displayLoadingState() {
        binding.group.visibility = View.INVISIBLE
        binding.animationLoding.visibility = View.VISIBLE
    }

    private fun displayFailureState() {
        binding.group.visibility = View.INVISIBLE
        binding.animationLoding.setAnimation(R.raw.error_movie)
        binding.animationLoding.playAnimation()
        binding.animationLoding.visibility = View.VISIBLE
    }

}


