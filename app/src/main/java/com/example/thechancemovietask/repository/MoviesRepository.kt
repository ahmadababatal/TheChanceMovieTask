package com.example.thechancemovietask.repository

import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.util.DataMoviesState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

     fun getTrendingMovies(): Flow<DataMoviesState<MoviesTotalResults>>

     fun getMoviesByMovieKeyword(keywordQuery:String): Flow<DataMoviesState<MoviesTotalResults>>

     fun getPopularMovies(): Flow<DataMoviesState<MoviesTotalResults>>

     fun getSimilarMovieByMovieId(movieId:String): Flow<DataMoviesState<MoviesTotalResults>>

     fun getListOfMovie(): Flow<DataMoviesState<MoviesTotalResults>>
}