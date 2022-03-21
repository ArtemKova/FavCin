package com.ka.favcin.utils

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.loader.content.AsyncTaskLoader
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException

public class NetworkUtils {
    private val BASE_URL = "https://api.themoviedb.org/3/discover/movie"
    private val BASE_URL_VIDEOS = "https://api.themoviedb.org/3/movie/%s/videos"
    private val BASE_URL_REVIEWS = "https://api.themoviedb.org/3/movie/%s/reviews"


    private val BASE_POSTER_URL = "https://image.tmdb.org/t/p/"
    val SMALL_POSTER_SIZE = "w185"
    val BIG_POSTER_SIZE = "w780"


    private val PARAMS_API_KEY = "api_key"
    private val PARAMS_LANGUAGE = "language"
    private val PARAMS_SORT_BY = "sort_by"
    private val PARAMS_PAGE = "page"
    private val PARAMS_MIN_VOTE_COUNT = "vote_count.gte"

    private val API_KEY = "f073b48419e738d4968dec1b725b9ede"

    private val SORT_BY_POPULARITY = "popularity.desc"
    private val SORT_BY_TOP_RATED = "vote_average.desc"
    private val MIN_VOTE_COUNT_VALUE = "1000"

    val POPULARITY = 0
    val TOP_RATED = 1

    fun buildURLToReviews(id: Int, lang: String?): URL? {
        val uri = Uri.parse(String.format(BASE_URL_REVIEWS, id)).buildUpon()
            .appendQueryParameter(PARAMS_API_KEY, API_KEY)
            .appendQueryParameter(PARAMS_LANGUAGE, lang)
            .build()
        try {
            return URL(uri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }


    fun buildURLToVideos(id: Int, lang: String?): URL? {
        val uri = Uri.parse(String.format(BASE_URL_VIDEOS, id)).buildUpon()
            .appendQueryParameter(PARAMS_API_KEY, API_KEY)
            .appendQueryParameter(PARAMS_LANGUAGE, lang)
            .build()
        try {
            return URL(uri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }


    fun buildURL(sortBy: Int, page: Int, lang: String?): URL? {
        var result: URL? = null
        val methodOfSort: String
        methodOfSort = if (sortBy == POPULARITY) {
            SORT_BY_POPULARITY
        } else {
            SORT_BY_TOP_RATED
        }
        val uri = Uri.parse(BASE_URL).buildUpon()
            .appendQueryParameter(PARAMS_API_KEY, API_KEY)
            .appendQueryParameter(PARAMS_LANGUAGE, lang)
            .appendQueryParameter(PARAMS_SORT_BY, methodOfSort)
            .appendQueryParameter(PARAMS_MIN_VOTE_COUNT, MIN_VOTE_COUNT_VALUE)
            .appendQueryParameter(PARAMS_PAGE, Integer.toString(page))
            .build()
        try {
            result = URL(uri.toString())
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return result
    }

    fun getJSONForVideos(id: Int, lang: String?): JSONObject? {
        var result: JSONObject? = null
        val url = buildURLToVideos(id, lang)
        try {
            result = JSONLoadTask().execute(url).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return result
    }

    fun getJSONForReviews(id: Int, lang: String?): JSONObject? {
        var result: JSONObject? = null
        val url = buildURLToReviews(id, lang)
        try {
            result = JSONLoadTask().execute(url).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return result
    }

    fun getJSONFromNetwork(sortBy: Int, page: Int, lang: String?): JSONObject? {
        var result: JSONObject? = null
        val url = buildURL(sortBy, page, lang)
        try {
            result = JSONLoadTask().execute(url).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return result
    }

    class JSONLoader(context: Context, private val bundle: Bundle?) :
        AsyncTaskLoader<JSONObject?>(context) {
        private var onStartLoadingListener: OnStartLoadingListener? = null

        interface OnStartLoadingListener {
            fun onStartLoading()
        }

        fun setOnStartLoadingListener(onStartLoadingListener: OnStartLoadingListener?) {
            this.onStartLoadingListener = onStartLoadingListener
        }

        override fun onStartLoading() {
            super.onStartLoading()
            if (onStartLoadingListener != null) {
                onStartLoadingListener!!.onStartLoading()
            }
            forceLoad()
        }

        override fun loadInBackground(): JSONObject? {
            if (bundle == null) {
                return null
            }
            val urlAsString = bundle.getString("url")
            var url: URL? = null
            try {
                url = URL(urlAsString)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            var result: JSONObject? = null
            if (url == null) {
                return result
            }
            var connection: HttpURLConnection? = null
            try {
                connection = url.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                val reader = BufferedReader(inputStreamReader)
                val builder = StringBuilder()
                var line = reader.readLine()
                while (line != null) {
                    builder.append(line)
                    line = reader.readLine()
                }
                result = JSONObject(builder.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            return result
        }
    }

    private class JSONLoadTask :
        AsyncTask<URL?, Void?, JSONObject?>() {
        protected override fun doInBackground(vararg urls: URL?): JSONObject? {
            var result: JSONObject? = null
            if (urls == null || urls.size == 0) {
                return result
            }
            var connection: HttpURLConnection? = null
            try {
                connection = urls[0]?.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                val reader = BufferedReader(inputStreamReader)
                val builder = StringBuilder()
                var line = reader.readLine()
                while (line != null) {
                    builder.append(line)
                    line = reader.readLine()
                }
                result = JSONObject(builder.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
            return result
        }


    }
}