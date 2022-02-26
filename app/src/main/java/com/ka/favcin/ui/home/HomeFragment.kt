package com.ka.favcin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.adapters.MovieAdapter
import com.ka.favcin.data.Movie
import com.ka.favcin.utils.JSONUtils
import com.ka.favcin.utils.NetworkUtils
import com.ka.favcin.utils.api.ApiFactory
import com.ka.favcin.utils.pojo.Results
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var recyclerViewPosters: RecyclerView? = null

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val  movieAdapter= MovieAdapter()

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        recyclerViewPosters = root.findViewById(R.id.recyclerViewPosters)
        recyclerViewPosters!!.layoutManager = GridLayoutManager(context, 2)
        recyclerViewPosters!!.adapter = movieAdapter
        val disposable = ApiFactory.apiService.getMoviesFromApi()
            .map { it.results }
//            .flatMap {ApiFactory.apiService1.getLittlePostersFromApi(posterPath = it.toString())}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var movies: MutableList<Results> = it as MutableList<Results>


                movieAdapter?.setMovies(movies)
                recyclerViewPosters!!.setAdapter(movieAdapter)

                Log.d("TEST_OF_LOADING_DATA", "Success  ${movies}")
            }, {

            })
        compositeDisposable.add(disposable)






        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}



