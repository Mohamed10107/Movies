package com.coderbot.movies.data.repository

import com.coderbot.movies.data.api.response.GetMoviesResponse
import com.coderbot.movies.data.api.webservice.MoviesAPI
import com.coderbot.movies.data.database.Database
import com.coderbot.movies.domain.repository.MoviesRepository
import com.coderbot.movies.domain.utils.Constants
import io.reactivex.Single
import retrofit2.Retrofit

class MoviesRepositoryImplementation constructor(protected var retrofit: Retrofit) : MoviesRepository
{
    private var api: MoviesAPI = retrofit.create(MoviesAPI::class.java)

    override fun getMovies(page: Int): Single<GetMoviesResponse>
    {
        return api.getMovies(api_key = Constants.API_KEY, page = page)
    }
}