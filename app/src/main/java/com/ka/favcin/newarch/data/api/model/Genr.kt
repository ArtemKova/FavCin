package com.ka.favcin.newarch.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genr(
    @SerializedName("genre_ids")
    @Expose
    val genre_ids:Int)
