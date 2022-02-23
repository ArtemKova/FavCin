package com.ka.favcin.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class Movie(

    id: Int,
    voteCount: Int,
    title: String?,
    originalTitle: String?,
    overview: String?,
    posterPath: String?,
    bigPosterPath: String?,
    backdropPath: String?,
    voteAverage: Double,
    releaseDate: String?
) {
    @PrimaryKey(autoGenerate = true)
    private var uniqueId = 0
    private var id = 0
    private var voteCount = 0
    private var title: String? = null
    private var originalTitle: String? = null
    private var overview: String? = null
    private var posterPath: String? = null
    private var bigPosterPath: String? = null
    private var backdropPath: String? = null
    private var voteAverage = 0.0
    private var releaseDate: String? = null

    fun Movie(
        uniqueId: Int,
        id: Int,
        voteCount: Int,
        title: String?,
        originalTitle: String?,
        overview: String?,
        posterPath: String?,
        bigPosterPath: String?,
        backdropPath: String?,
        voteAverage: Double,
        releaseDate: String?
    ) {
        this.uniqueId = uniqueId
        this.id = id
        this.voteCount = voteCount
        this.title = title
        this.originalTitle = originalTitle
        this.overview = overview
        this.posterPath = posterPath
        this.bigPosterPath = bigPosterPath
        this.backdropPath = backdropPath
        this.voteAverage = voteAverage
        this.releaseDate = releaseDate
    }

    @Ignore
    fun Movie(
        id: Int,
        voteCount: Int,
        title: String?,
        originalTitle: String?,
        overview: String?,
        posterPath: String?,
        bigPosterPath: String?,
        backdropPath: String?,
        voteAverage: Double,
        releaseDate: String?
    ) {
        this.id = id
        this.voteCount = voteCount
        this.title = title
        this.originalTitle = originalTitle
        this.overview = overview
        this.posterPath = posterPath
        this.bigPosterPath = bigPosterPath
        this.backdropPath = backdropPath
        this.voteAverage = voteAverage
        this.releaseDate = releaseDate
    }

    fun getUniqueId(): Int {
        return uniqueId
    }

    fun setUniqueId(uniqueId: Int) {
        this.uniqueId = uniqueId
    }

    fun getBigPosterPath(): String? {
        return bigPosterPath
    }

    fun setBigPosterPath(bigPosterPath: String?) {
        this.bigPosterPath = bigPosterPath
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getVoteCount(): Int {
        return voteCount
    }

    fun setVoteCount(voteCount: Int) {
        this.voteCount = voteCount
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getOriginalTitle(): String? {
        return originalTitle
    }

    fun setOriginalTitle(originalTitle: String?) {
        this.originalTitle = originalTitle
    }

    fun getOverview(): String? {
        return overview
    }

    fun setOverview(overview: String?) {
        this.overview = overview
    }

    fun getPosterPath(): String? {
        return posterPath
    }

    fun setPosterPath(posterPath: String?) {
        this.posterPath = posterPath
    }

    fun getBackdropPath(): String? {
        return backdropPath
    }

    fun setBackdropPath(backdropPath: String?) {
        this.backdropPath = backdropPath
    }

    fun getVoteAverage(): Double {
        return voteAverage
    }

    fun setVoteAverage(voteAverage: Double) {
        this.voteAverage = voteAverage
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String?) {
        this.releaseDate = releaseDate
    }
}