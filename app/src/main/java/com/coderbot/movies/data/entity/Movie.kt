package com.coderbot.movies.data.entity

import java.io.Serializable

class Movie : Serializable
{
    var id: Long = 0
    var title: String = ""
    var image: String = ""
    var description: String = ""
    var rate: Double = 0.0
}