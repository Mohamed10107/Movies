package com.coderbot.movies.presentation.view.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.coderbot.movies.R
import com.coderbot.movies.data.entity.Movie
import com.coderbot.movies.domain.utils.Constants
import com.coderbot.movies.domain.utils.Views
import com.coderbot.movies.presentation.interfaces.BottomReachListener
import com.coderbot.movies.presentation.interfaces.MovieSelectionListener
import java.util.ArrayList

class MoviesAdapter constructor(val context: Context, var bottomReachListener: BottomReachListener, var movieSelectionListener: MovieSelectionListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private var movies: ArrayList<Movie> = ArrayList()

    fun setData(movies: ArrayList<Movie>)
    {
        this@MoviesAdapter.movies = ArrayList()
        this@MoviesAdapter.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun addData(movies: ArrayList<Movie>)
    {
        this@MoviesAdapter.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder
    {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_movie_item, parent, false))
    }

    override fun getItemCount(): Int
    {
        return movies.size
    }

    private fun getItem(position: Int): Movie
    {
        return movies.get(position)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)
    {
        val holder: ViewHolder = viewHolder as ViewHolder
        val movie = getItem(position)

        holder.title.text = movie.title
        Views.ImageLoader.load(context, holder.image, Constants.IMAGES_URL + movie.image.replace("/", ""), "")

        if (position == itemCount - 1)
        {
            bottomReachListener.onReachBottom()
        }
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        @BindView(R.id.image)
        internal lateinit var image: ImageView

        @BindView(R.id.title)
        internal lateinit var title: TextView

        init
        {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {
                movieSelectionListener.onMovieSelected(getItem(layoutPosition))
            }
        }
    }
}