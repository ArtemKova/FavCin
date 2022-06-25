package com.ka.favcin.newarch.di

import android.app.Application
import com.ka.favcin.database.AppDatabase
import com.ka.favcin.newarch.data.db.MovieDetailListDao
import dagger.Module
import dagger.Provides

@Module

interface DataModule {

    companion object{
        @Provides
        fun provideMovieDataListDao(
            application: Application
        ):MovieDetailListDao{
            return AppDatabase.getInstance(application).movieDetailListDao()
        }
    }

}