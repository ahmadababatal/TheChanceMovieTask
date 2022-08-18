package com.example.thechancemovietask.repository

import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.network.MoviesService
import com.example.thechancemovietask.util.Constants.MOVIE
import com.example.thechancemovietask.util.Constants.POPULAR
import com.example.thechancemovietask.util.Constants.SEARCHING
import com.example.thechancemovietask.util.Constants.SIMILAR
import com.example.thechancemovietask.util.Constants.TOP_LISTS
import com.example.thechancemovietask.util.Constants.TRENDING
import com.example.thechancemovietask.util.DataMoviesState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val movieService: MoviesService
) : MoviesRepository{

    override fun getTrendingMovies(): Flow<DataMoviesState<MoviesTotalResults>> {
        return emitting(TRENDING)
    }

    override fun getMoviesByMovieKeyword(keywordQuery: String): Flow<DataMoviesState<MoviesTotalResults>> {
        return emitting("${SEARCHING}$keywordQuery")
    }

    override fun getPopularMovies(): Flow<DataMoviesState<MoviesTotalResults>> {
        return emitting(POPULAR)
    }

    override fun getSimilarMovieByMovieId(movieId: String): Flow<DataMoviesState<MoviesTotalResults>> {
        return emitting("${MOVIE}$movieId${SIMILAR}")
    }

    override fun getListOfMovie(): Flow<DataMoviesState<MoviesTotalResults>> {
        return emitting(TOP_LISTS)
    }

    private fun emitting(queries:String): Flow<DataMoviesState<MoviesTotalResults>> {
        return flow {
            emit(DataMoviesState.Loading)
            emit(movieService.getMovies(queries))
        }
    }

}