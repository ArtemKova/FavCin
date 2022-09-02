package com.ka.favcin.newarch.di

import android.app.Application
import com.ka.core.data.db.MovieDetailListDao
import com.ka.core.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module

interface DataModule {

    companion object{
        @Provides
        fun provideMovieDataListDao(
            application: Application
        ): com.ka.core.data.db.MovieDetailListDao {
            return AppDatabase.getInstance(application).movieDetailListDao()
        }
    }

}