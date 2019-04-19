package com.coderbot.movies.presentation.view.movie_details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.coderbot.movies.R
import com.coderbot.movies.data.entity.Movie
import com.coderbot.movies.domain.utils.Constants
import com.coderbot.movies.domain.utils.Views
import de.hdodenhof.circleimageview.CircleImageView

class MovieDetailsActivity : AppCompatActivity()
{
    @BindView(R.id.image)
    lateinit var image: CircleImageView

    @BindView(R.id.title)
    lateinit var title: TextView

    @BindView(R.id.rate)
    lateinit var rate: TextView

    @BindView(R.id.description)
    lateinit var description: TextView

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        ButterKnife.bind(this@MovieDetailsActivity)

        movie = intent.getSerializableExtra("movie") as Movie

        init()
    }

    private fun init()
    {
        title.text = movie.title
        rate.text = "${movie.rate}"
        description.text = movie.description

        Views.ImageLoader.load(this@MovieDetailsActivity, image, Constants.IMAGES_URL + movie.image.replace("/", ""), "")
    }
}