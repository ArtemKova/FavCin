package com.ka.favcin.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.ka.favcin.database.AppDatabase
import com.ka.favcin.utils.api.ApiFactory
import com.ka.favcin.utils.pojo.Results
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()

    init {
        loadData()
    }

    private val db = AppDatabase.getInstance(application)
    val filmList = db.movieDetailListDao().getResults()

    fun getDetailInfo(id: Int): LiveData<List<Results>> {
        return db.movieDetailListDao().getFilmInfo(id)
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text
    fun loadData() {
        var movies: MutableList<Results>
        val disposable = ApiFactory.apiService.getMoviesFromApi()
            .map { it.results }
//            .flatMap {ApiFactory.apiService1.getLittlePostersFromApi(posterPath = it.toString())}
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.movieDetailListDao().insertMovie(it as List<Results>)
                movies = it as MutableList<Results>
                Log.d("TEST_OF_LOADING_DATA", "Success  $it")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "${it.message}")

            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}