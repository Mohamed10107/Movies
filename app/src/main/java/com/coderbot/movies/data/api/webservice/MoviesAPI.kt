package com.coderbot.movies.data.api.webservice

import com.coderbot.movies.data.api.response.GetMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI
{
    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Single<GetMoviesResponse>
}