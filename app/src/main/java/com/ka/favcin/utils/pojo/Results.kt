package com.ka.favcin.utils.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_detail_list")
data class Results(
//     @SerializedName("adult")
//     @Expose
//     private val adult: Boolean? = null,
//
//     @SerializedName("backdrop_path")
//     @Expose
//     private val backdropPath: String? = null,
//
//    @SerializedName("genre_ids")
//    @Expose
//     val genreIds: List<Integer>,

    @PrimaryKey
    @SerializedName("id")
    @Expose private var id: Int? = null,

    @SerializedName("original_language")
    @Expose
    private var originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    private var originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    private var overview: String? = null,

    @SerializedName("popularity")
    @Expose
    private var popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    private var posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null,

    @SerializedName("title")
    @Expose
    private var title: String? = null,

//    @SerializedName("video")
//    @Expose
//    private val video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    private var voteAverage: Double? = null,

    @SerializedName("vote_count")
    @Expose
    private var voteCount: Int? = null,

    @SerializedName("with_cast")
    @Expose
    private var people: String? = null)
// {
//
//
//    fun getPosterPath(): String? {
//        return posterPath
//
//    }
//
//    fun setPosterPath(posterPath: String?) {
//        this.posterPath = posterPath
//    }
//
//
//
//    fun getId(): Int? {
//        return id
//    }
//
//    fun setId(id: Int) {
//        this.id = id
//    }
//
//    fun getOriginalLanguage(): String? {
//        return originalLanguage
//    }
//
//    fun getOriginalLanguage(originalLanguage: String) {
//        this.originalLanguage = originalLanguage
//    }
//
//    fun getVoteCount(): Int? {
//        return voteCount
//    }
//
//    fun setVoteCount(voteCount: Int) {
//        this.voteCount = voteCount
//    }
//
//    fun getTitle(): String? {
//        return title
//    }
//
//    fun setTitle(title: String?) {
//        this.title = title
//    }
//
//    fun getOriginalTitle(): String? {
//        return originalTitle
//    }
//
//    fun setOriginalTitle(originalTitle: String?) {
//        this.originalTitle = originalTitle
//    }
//
//    fun getOverview(): String? {
//        return overview
//    }
//
//    fun setOverview(overview: String?) {
//        this.overview = overview
//    }
//
//
//    fun getVoteAverage(): Double? {
//        return voteAverage
//    }
//
//    fun setVoteAverage(voteAverage: Double) {
//        this.voteAverage = voteAverage
//    }
//
//    fun getPopularity(): Double? {
//        return popularity
//    }
//
//    fun setPopularity(popularity: Double) {
//        this.popularity = popularity
//    }
//
//    fun getReleaseDate(): String? {
//        return releaseDate
//    }
//
//    fun setReleaseDate(releaseDate: String?) {
//        this.releaseDate = releaseDate
//    }
//
//    fun getPeople(): String? {
//        return people
//    }
//
//    fun setPeople(people: String?) {
//        this.people = people
//    }


//}

