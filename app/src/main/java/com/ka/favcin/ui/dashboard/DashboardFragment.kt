package com.ka.favcin.ui.dashboard

import android.graphics.ImageFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.adapters.ActorAdapter
import com.ka.favcin.adapters.MovieAdapter
import com.ka.favcin.databinding.FragmentDashboardBinding
import com.ka.favcin.databinding.FragmentHomeBinding
import com.ka.favcin.ui.MAIN
import com.ka.favcin.ui.home.HomeViewModel
import com.ka.favcin.utils.api.ApiFactory
import com.ka.favcin.utils.api.ApiService
import com.ka.favcin.utils.pojo.Casts
import com.ka.favcin.utils.pojo.Results
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var homeViewModel: HomeViewModel
    private val compositeDisposable = CompositeDisposable()
    private var recyclerViewActors: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = arguments?.getInt("id")
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        val actorAdapter = ActorAdapter()
        Log.d("ButtoPicture", "Кнопка сработала3 $id  ")
        var genr: Casts? = null
        recyclerViewActors = binding.root.findViewById(R.id.recycler_stars)
        recyclerViewActors!!.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        recyclerViewActors!!.adapter = actorAdapter
        lateinit var movie: Results
        if (id != null) {
            homeViewModel.getDetailInfo(id).observe(viewLifecycleOwner, Observer { it ->
                movie = it[0]
//                val mov = movie.map{it}
                Log.d("ButtoPicture", "Кнопка сработала4 $movie  ")
                Picasso.get()
                    .load(ApiFactory.BASE_POSTER_URL + ApiService.BIG_POSTER_SIZE + movie.getPosterPath())
                    .into(bigPosterView)
                name_movie.text = movie.getTitle()
                describe_movie.text = movie.getOverview()
                ratingBar.rating = movie.getVoteAverage()?.toFloat() ?: 3f
            })
            val disposable = ApiFactory.apiService.getActors(idMovie = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("TEST_OF_LOADING_DATA2", "Success  ${it.cast}         ")
           genr = it
                    var t = it.cast
                    actorAdapter.actors = t
                }, {
                    Log.d("TEST_OF_LOADING_DATA1", "NoSuccess $it ")
                })
            compositeDisposable.add(disposable)
//        val textView: TextView = binding.root.findViewById(R.id.text_dashboard)
            dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
            })


        }

        return binding.root
    }
    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
