package com.ka.favcin.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ka.favcin.data.Movie
import com.ka.favcin.utils.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val  compositeDisposable = CompositeDisposable()

    init {
        loadData()
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    private fun loadData() {
        val disposable = ApiFactory.apiService.getMoviesFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({


                Log.d("TEST_OF_LOADING_DATA", "Success  $it")
            }, {

            })
        compositeDisposable.add(disposable)
    }
    override fun onCleared() {
        super.onCleared()
//        compositeDisposable.dispose()
    }
}