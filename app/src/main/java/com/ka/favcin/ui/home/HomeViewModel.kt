package com.ka.favcin.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.ka.core.data.db.MovieDetailListDao
import com.ka.favcin.utils.api.ApiFactory
import com.ka.core.data.db.Results
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(

    private val db: com.ka.core.data.db.MovieDetailListDao
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        loadData()
    }


    val filmList = db.getResults()

    fun getDetailInfo(id: Int): LiveData<List<com.ka.core.data.db.Results>> {
        return db.getFilmInfo(id)
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text
    fun loadData() {
        var movies: MutableList<com.ka.core.data.db.Results>
        val disposable = ApiFactory.apiService.getMoviesFromApi()
            .map { it.results }
            .flatMap {ApiFactory.apiService1.getLittlePostersFromApi(posterPath = it.toString())}
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.insertMovie(it as List<com.ka.core.data.db.Results>)
                movies = it as MutableList<com.ka.core.data.db.Results>
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