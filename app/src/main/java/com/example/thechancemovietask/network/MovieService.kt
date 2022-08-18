package com.example.thechancemovietask.network

import com.example.thechancemovietask.model.MoviesTotalResults
import com.example.thechancemovietask.util.DataMoviesState
import com.google.gson.Gson
import okhttp3.OkHttpClient

class MovieService : MoviesService {

    override fun getMovies(queries:String): DataMoviesState<MoviesTotalResults> {
        val client = OkHttpClient()
        val response = client.newCall(RequestMovies.makeMoviesRequest(queries)).execute()
        return if (response.isSuccessful)
            Gson().fromJson(
                response.body?.string(),
                MoviesTotalResults::class.java
            ).run {
                DataMoviesState.Success(this)
            }
        else DataMoviesState.Error(response.message)
    }

    // TRENDING
    // ${SEARCHING}$keywordQuery
    // ${POPULAR}$pageQuery
    // ${MOVIE}$movieId${SIMILAR}
}