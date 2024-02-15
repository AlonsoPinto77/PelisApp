package com.example.data.repositories.movie

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.example.data.network.DomainMapper
import com.example.domain.interactors.movies.Movie

@Entity(tableName = "movie", indices = [Index(value = ["id"], unique = true)])
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    val id : Int,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("release_date")
    val releaseDate : String,
    var page : Int
): DomainMapper<Movie> {
    override fun mapToDomainModel(): Movie {
        return Movie(id, posterUrl, title, overview,
            voteAverage, releaseDate, page)
    }
}