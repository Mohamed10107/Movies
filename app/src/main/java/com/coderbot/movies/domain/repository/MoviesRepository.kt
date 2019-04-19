package com.coderbot.movies.domain.repository

import com.coderbot.movies.data.api.response.GetMoviesResponse
import io.reactivex.Single

interface MoviesRepository
{
    fun getMovies(page: Int): Single<GetMoviesResponse>
}