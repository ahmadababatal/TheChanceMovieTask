package com.example.thechancemovietask.network

import com.example.thechancemovietask.util.Constants.MOVIE_BASE_URL
import okhttp3.Request

// Trending
// https://api.themoviedb.org/3/trending/movie/week?api_key=a992c8849abde007ba465cd72807d917

// Search
// https://api.themoviedb.org/3/search/movie?&query=prey&api_key=a992c8849abde007ba465cd72807d917

// Popular
// https://api.themoviedb.org/3/movie/popular?api_key=a992c8849abde007ba465cd72807d917&US

// Similar
// https://api.themoviedb.org/3/movie/12451/similar?api_key=a992c8849abde007ba465cd72807d917

object  RequestMovies {
    fun makeMoviesRequest(queries:String): Request{
        val url = "${MOVIE_BASE_URL}$queries"
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }
}