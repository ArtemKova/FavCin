package com.ka.favcin.newarch.data.api

import android.media.Image
import com.ka.favcin.newarch.data.api.model.Casts
import com.ka.favcin.newarch.data.api.model.Films
import com.ka.favcin.newarch.data.api.model.Genress

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("discover/movie")
    fun getMoviesFromApi(
        @Query(PARAMS_API_KEY) api_key: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = "ru-RU",
        @Query(PARAMS_SORT_BY) sort_by: String = "popularity.desc",
        @Query(PARAMS_PAGE) page: Int = 1,
        @Query(CAST) people: String = "with_cast"

//   @Query(PARAMS_MIN_VOTE_COUNT)vote_count:String = "vote_count.gte",
//   @Query(SORT_BY_POPULARITY)popularity:String = "popularity.desc",
//   @Query(SORT_BY_TOP_RATED)top_rated:String = "vote_average.desc",
//   @Query(MIN_VOTE_COUNT_VALUE)min_vote_count_value:String = "1000"

    ): Single<Films>

    @GET("t/p")
    fun getLittlePostersFromApi(
        @Query(SMALL_POSTER_SIZE) posterPath: String?
    ): Single<String?>

    @GET("t/p")
    fun getBigPostersFromApi(
        @Query(BIG_POSTER_SIZE) posterPath: String
    ): Single<Image>

    @GET("genre/movie/list")
    fun getGenreList(
        @Query(PARAMS_API_KEY) api_key: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = "ru-RU"
    ): Observable<Genress>

    @GET("movie/{movie_id}/credits")
    fun getActors(
        @Path("movie_id") idMovie:Int,
        @Query(PARAMS_API_KEY) api_key: String = API_KEY,
        @Query(PARAMS_LANGUAGE) language: String = "ru-RU"
    ): Observable<Casts>




    companion object {
        private const val PARAMS_API_KEY = "api_key"
        private const val PARAMS_LANGUAGE = "language"
        private const val PARAMS_SORT_BY = "sort_by"
        private const val PARAMS_PAGE = "page"
        private const val PARAMS_MIN_VOTE_COUNT = "vote_count.gte"
        private const val API_KEY = "f073b48419e738d4968dec1b725b9ede"
        private const val SORT_BY_POPULARITY = "popularity.desc"
        private const val SORT_BY_TOP_RATED = "vote_average.desc"
        private const val MIN_VOTE_COUNT_VALUE = "1000"
        private const val CAST = "with_cast"

        const val SMALL_POSTER_SIZE = "w185"
        const val BIG_POSTER_SIZE = "w780"
        val POPULARITY = 0
        val TOP_RATED = 1
    }
}