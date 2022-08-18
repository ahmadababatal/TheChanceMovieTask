package com.example.thechancemovietask.network

import com.example.thechancemovietask.util.Constants.MOVIE_BASE_URL
import okhttp3.Request

object  RequestMovies {
    fun makeMoviesRequest(queries:String): Request{
        val url = "${MOVIE_BASE_URL}$queries"
        return Request.Builder()
            .url(url)
            .get()
            .build()
    }
}