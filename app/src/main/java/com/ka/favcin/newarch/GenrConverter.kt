package com.ka.favcin.newarch

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.util.stream.Collectors

 class GenrConverter {
    @RequiresApi(Build.VERSION_CODES.N)
    @TypeConverter
    fun fromListGenr(list: List<String?>): String {
        return list.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
     fun toListGenr(data: String): List<String> {
        return (data.split(","))
    }
}