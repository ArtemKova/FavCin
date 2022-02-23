package com.ka.favcin.utils

import android.util.Log
import com.ka.favcin.data.Movie
import com.ka.favcin.data.Review
import com.ka.favcin.data.Trailer
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class JSONUtils {

    private val KEY_RESULTS = "results"

    //Для отзывов
    private val KEY_AUTHOR = "author"
    private val KEY_CONTENT = "content"

    //Для видео
    private val KEY_KEY_OF_VIDEO = "key"
    private val KEY_NAME = "name"
    private val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="


    //Вся информация о фильме
    private val KEY_VOTE_COUNT = "vote_count"
    private val KEY_ID = "id"
    private val KEY_TITLE = "title"
    private val KEY_ORIGINAL_TITLE = "original_title"
    private val KEY_OVERVIEW = "overview"
    private val KEY_POSTER_PATH = "poster_path"
    private val KEY_BACKDROP_PATH = "backdrop_path"
    private val KEY_VOTE_AVERAGE = "vote_average"
    private val KEY_RELEASE_DATE = "release_date"
    private val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
    val SMALL_POSTER_SIZE = "w185"
    val BIG_POSTER_SIZE = "w780"

    fun getReviewsFromJSON(jsonObject: JSONObject?): ArrayList<Review>? {
        val result: ArrayList<Review> = ArrayList<Review>()
        if (jsonObject == null) {
            return result
        }
        try {
            val jsonArray = jsonObject.getJSONArray(KEY_RESULTS)
            for (i in 0 .. jsonArray.length()) {
                val jsonObjectReview = jsonArray.getJSONObject(i)
                val author = jsonObjectReview.getString(KEY_AUTHOR)
                val content = jsonObjectReview.getString(KEY_CONTENT)
                val review = Review(author, content)
                result.add(review)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }

    fun getTtrailerFromJSON(jsonObject: JSONObject?): ArrayList<Trailer>? {
        val result: ArrayList<Trailer> = ArrayList<Trailer>()
        if (jsonObject == null) {
            return result
        }
        try {
            val jsonArray = jsonObject.getJSONArray(KEY_RESULTS)
            for (i in 0 .. jsonArray.length()) {
                val jsonObjectTrailer = jsonArray.getJSONObject(i)
                val key = BASE_YOUTUBE_URL + jsonObjectTrailer.getString(KEY_KEY_OF_VIDEO)
                val name = jsonObjectTrailer.getString(KEY_NAME)
                val trailer = Trailer(key, name)
                result.add(trailer)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return result
    }

     fun getMoviesFromJSON(jsonObject: JSONObject?): ArrayList<Movie>? {
        val result: ArrayList<Movie> = ArrayList<Movie>()
        if (jsonObject == null) {
            return result
        }
        try {
            val jsonArray = jsonObject.getJSONArray(KEY_RESULTS)

            for (i in 0 .. jsonArray.length()) {
                val objectMovie = jsonArray.getJSONObject(i)
                val id = objectMovie.getInt(KEY_ID)
                val voteCount = objectMovie.getInt(KEY_VOTE_COUNT)
                val title = objectMovie.getString(KEY_TITLE)
                val originalTitle = objectMovie.getString(KEY_ORIGINAL_TITLE)
                val overview = objectMovie.getString(KEY_OVERVIEW)
                val postrPath =
                    BASE_POSTER_URL + SMALL_POSTER_SIZE + objectMovie.getString(KEY_POSTER_PATH)
                val bigPostrPath =
                    BASE_POSTER_URL + BIG_POSTER_SIZE + objectMovie.getString(KEY_POSTER_PATH)
                val backdropPath = objectMovie.getString(KEY_BACKDROP_PATH)
                val voteAverage = objectMovie.getDouble(KEY_VOTE_AVERAGE)
                val releaseDate = objectMovie.getString(KEY_RELEASE_DATE)
                val movie = Movie(
                    id,
                    voteCount,
                    title,
                    originalTitle,
                    overview,
                    postrPath,
                    bigPostrPath,
                    backdropPath,
                    voteAverage,
                    releaseDate
                )
                result.add(movie)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.i("Error", "Oшибка")
        }
        return result
    }
}