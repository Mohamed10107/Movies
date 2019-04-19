package com.coderbot.movies.presentation.view_model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.coderbot.movies.data.entity.Movie
import com.coderbot.movies.domain.usecase.GetMovies
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel constructor(protected var getMovieUseCase: GetMovies) : ViewModel()
{
    private var page = 0

    private val moviesObserver = MutableLiveData<ArrayList<Movie>>()

    fun observeMovies(): MutableLiveData<ArrayList<Movie>>
    {
        return moviesObserver
    }

    private val errorObserver = MutableLiveData<String>()

    fun observeError(): MutableLiveData<String>
    {
        return errorObserver
    }

    fun getMovies()
    {
        getMovieUseCase.getMovies(++page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<ArrayList<Movie>>
        {
            override fun onSubscribe(d: Disposable)
            {

            }

            override fun onSuccess(movies: ArrayList<Movie>)
            {
                moviesObserver.postValue(movies)
            }

            override fun onError(e: Throwable)
            {
                errorObserver.postValue(e.message)
            }
        })
    }
}