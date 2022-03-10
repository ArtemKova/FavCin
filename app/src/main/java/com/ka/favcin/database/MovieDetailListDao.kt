package com.ka.favcin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.ka.favcin.utils.pojo.Results

@Dao
interface MovieDetailListDao {
    @Query("SELECT * FROM movie_detail_list ORDER BY popularity")
    fun getResults(): LiveData<List<Results>>
@Query("SELECT*FROM movie_detail_list WHERE id==:id")
    fun getFilmInfo(id:Int?):LiveData<List<Results>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insertMovie(filmList:List<Results>)


}