package com.coderbot.movies.domain.usecase

import com.coderbot.movies.data.entity.Movie
import com.coderbot.movies.domain.mapper.Mappers
import com.coderbot.movies.domain.repository.MoviesRepository
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class GetMovies constructor(protected var repository: MoviesRepository)
{
    fun getMovies(page: Int): Single<ArrayList<Movie>>
    {
        return Single.create { emitter ->
            repository.getMovies(page).map(Mappers.movieMapper).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(object : SingleObserver<ArrayList<Movie>>
            {
                override fun onSubscribe(d: Disposable)
                {

                }

                override fun onSuccess(movies: ArrayList<Movie>)
                {
                    emitter.onSuccess(movies)
                }

                override fun onError(e: Throwable)
                {
                    e.printStackTrace()
                    emitter.onError(e)
                }
            })
        }
    }
}