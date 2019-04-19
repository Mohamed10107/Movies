package com.coderbot.movies.presentation.view.main

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.coderbot.movies.R
import com.coderbot.movies.data.entity.Movie
import com.coderbot.movies.domain.utils.Views
import com.coderbot.movies.presentation.interfaces.BottomReachListener
import com.coderbot.movies.presentation.interfaces.MovieSelectionListener
import com.coderbot.movies.presentation.view.movie_details.MovieDetailsActivity
import com.coderbot.movies.presentation.view_model.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity()
{
    @BindView(R.id.movies)
    protected lateinit var movies: RecyclerView

    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: MoviesAdapter
    private lateinit var loading: Views.LoadingView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this@MainActivity)
        loading = Views.LoadingView(this@MainActivity)

        init()
        initListeners()
        getMovies()
    }

    private fun init()
    {
        movies.setHasFixedSize(true)
        movies.isNestedScrollingEnabled = false
        movies.layoutManager = GridLayoutManager(this, 2)
        adapter = MoviesAdapter(this, bottomReachListener = object : BottomReachListener
        {
            override fun onReachBottom()
            {
                getMovies()
            }
        }, movieSelectionListener = object : MovieSelectionListener
        {
            override fun onMovieSelected(movie: Movie)
            {
                openMovieDetails(movie)
            }
        })
        movies.adapter = adapter
        adapter.setData(ArrayList())
    }

    private fun initListeners()
    {
        viewModel.observeMovies().observe(this, Observer { movies ->
            loading.dismiss()
            if (movies != null)
            {
                adapter.addData(movies)
            }
            else
            {
                Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.observeError().observe(this, Observer { error ->
            loading.dismiss()
            if (error != null)
            {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getMovies()
    {
        loading.show()
        viewModel.getMovies()
    }

    private fun openMovieDetails(movie: Movie)
    {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}
