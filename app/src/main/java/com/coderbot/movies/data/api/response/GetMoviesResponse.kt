package com.coderbot.movies.data.api.response

import com.google.gson.annotations.SerializedName

class GetMoviesResponse
{
    @SerializedName("results")
    lateinit var movies: ArrayList<MovieData>

    class MovieData
    {
        @SerializedName("id")
        var id: Long = 0

        @SerializedName("vote_count")
        var votes: Int = 0

        @SerializedName("vote_average")
        var rate: Double = 0.0

        @SerializedName("original_title")
        var title: String = ""

        @SerializedName("poster_path")
        var image: String = ""

        @SerializedName("overview")
        var description: String = ""

        @SerializedName("release_date")
        var date: String = ""
    }
}