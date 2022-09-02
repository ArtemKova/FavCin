package com.ka.core.database
import android.content.Context
import androidx.room.*
import com.ka.core.data.db.MovieDetailListDao
import com.ka.core.data.db.Results
import com.ka.favcin.newarch.ListConverter

@Database(entities = [Results::class], version = 1,exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class AppDatabase:RoomDatabase() {
    companion object{
        private var db:AppDatabase?= null
        private val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context):AppDatabase{
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }


        }
    }
    abstract fun movieDetailListDao(): MovieDetailListDao
}