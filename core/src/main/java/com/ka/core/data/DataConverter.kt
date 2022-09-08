package com.ka.core.data

import androidx.room.TypeConverter
import java.util.*

class  DataConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}