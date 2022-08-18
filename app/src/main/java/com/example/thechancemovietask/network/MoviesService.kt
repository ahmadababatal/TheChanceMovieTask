package com.example.thechancemovietask.network

import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.util.DataMoviesState

interface MoviesService {
    fun getMovies(queries:String):DataMoviesState<MoviesTotalResults>
}