package com.example.thechancemovietask.util

object Constants {
    const val MOVIE_BASE_URL = "https://api.themoviedb.org/3/"
    const val MOVIE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    const val API_KEY = "a992c8849abde007ba465cd72807d917"

    const val TRENDING = "trending/movie/week?api_key=${API_KEY}"
    const val SEARCHING = "search/movie?api_key=$API_KEY&query="
    const val POPULAR = "movie/popular?api_key=$API_KEY"
    const val TOP_LISTS = "${MOVIE_BASE_URL}movie/top_rated?api_key=$API_KEY"
    const val MOVIE = "movie/"
    const val SIMILAR = "/similar?api_key=$API_KEY"


// Trending
// https://api.themoviedb.org/3/trending/movie/week?api_key=a992c8849abde007ba465cd72807d917

// Search
// https://api.themoviedb.org/3/search/movie?&query=prey&api_key=a992c8849abde007ba465cd72807d917

// Popular
// https://api.themoviedb.org/3/movie/popular?api_key=a992c8849abde007ba465cd72807d917&US

// Similar
// https://api.themoviedb.org/3/movie/12451/similar?api_key=a992c8849abde007ba465cd72807d917
}