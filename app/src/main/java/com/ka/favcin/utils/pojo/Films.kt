package com.ka.favcin.utils.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Films(
    @SerializedName("page")
    @Expose
     var page: Int = 0,
    @SerializedName("results")
    @Expose
     var results: List<Results?>? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0,

    @SerializedName("total_results")
    @Expose
     var totalResults: Int = 0

)