package com.ka.favcin.database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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
}