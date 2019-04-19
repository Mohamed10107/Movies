package com.coderbot.movies.presentation.interfaces

import com.coderbot.movies.data.entity.Movie

interface MovieSelectionListener
{
    fun onMovieSelected(movie: Movie)
}