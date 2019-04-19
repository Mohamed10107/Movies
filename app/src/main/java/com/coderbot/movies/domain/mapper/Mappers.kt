package com.coderbot.movies.domain.mapper

import com.coderbot.movies.data.api.response.GetMoviesResponse
import com.coderbot.movies.data.entity.Movie
import io.reactivex.functions.Function
import java.util.ArrayList

class Mappers
{
    companion object
    {
        var movieMapper = Function<GetMoviesResponse, ArrayList<Movie>> {
            val movies = ArrayList<Movie>()
            for (movieData in it.movies)
            {
                val movie = Movie()
                movie.id = movieData.id
                movie.title = movieData.title
                movie.image = movieData.image
                movie.description = movieData.description
                movie.rate = movieData.rate
                movies.add(movie)
            }
            return@Function movies
        }
    }
}