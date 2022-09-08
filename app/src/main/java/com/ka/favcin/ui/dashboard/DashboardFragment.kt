package com.ka.favcin.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.adapters.ActorAdapter
import com.ka.favcin.adapters.GenrAdapter
import com.ka.favcin.databinding.FragmentDashboardBinding
import com.ka.favcin.ui.home.HomeViewModel
import com.ka.favcin.utils.api.ApiFactory
import com.ka.core.data.api.ApiService
import com.ka.core.data.api.model.Casts
import com.ka.core.data.db.Results
import com.ka.favcin.ui.FanApp
import com.ka.favcin.ui.ViewModelFactory
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
//import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    //    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var homeViewModel: HomeViewModel
    private val compositeDisposable = CompositeDisposable()
    private var recyclerViewActors: RecyclerView? = null
    private var recyclerViewGenres: RecyclerView? = null
    lateinit var bigPosterView: ImageView
//    =requireView().findViewById(R.id.bigPosterView)
    lateinit var name_movie: TextView
    lateinit var describe_movie: TextView
    lateinit var ratingBar: RatingBar

    private val component by lazy {
        (requireActivity().application as FanApp).component
    }
    val sharedPreference =
        this.getActivity()?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var id = arguments?.getInt("id")
        val sharedPreference =
            this.getActivity()?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
//        dashboardViewModel =
//            ViewModelProvider(this)[DashboardViewModel::class.java]
        homeViewModel =
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        val actorAdapter = ActorAdapter()
        val genrAdapter = GenrAdapter()
        Log.d("ButtoPicture", "Кнопка сработала3 $id  ")
        var genr: com.ka.core.data.api.model.Casts? = null
        recyclerViewActors = binding.root.findViewById(R.id.recycler_stars)
        recyclerViewGenres = binding.root.findViewById(R.id.genrRecycler)
        recyclerViewActors!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewGenres!!.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewActors!!.adapter = actorAdapter
        recyclerViewGenres!!.adapter = genrAdapter

        lateinit var movie: com.ka.core.data.db.Results
        if (id != null) {
            homeViewModel.getDetailInfo(id).observe(viewLifecycleOwner, Observer { it ->
                movie = it[0]
                bigPosterView = view?.findViewById(R.id.bigPosterView)!!
                name_movie = view?.findViewById(R.id.name_movie)!!
                describe_movie = view?.findViewById(R.id.describe_movie)!!
                ratingBar =view?.findViewById(R.id.ratingBar)!!
//                val mov = movie.map{it}
                Log.d("ButtoPicture", "Кнопка сработала4 $movie  ")
                Picasso.get()
                    .load(ApiFactory.BASE_POSTER_URL + com.ka.core.data.api.ApiService.BIG_POSTER_SIZE + movie.posterPath)
                    .into(bigPosterView)
                name_movie.text = movie.title
                describe_movie.text = movie.overview
                ratingBar.rating = movie.voteAverage?.toFloat() ?: 3f
                var genre = movie.genreIds
                Log.d("TEST_OF_LOADING_DATA3", "Success  ${genre}         ")
                if (genre != null) {
                    var gen: MutableList<String> = mutableListOf()
                    for (i in genre) {
                        gen.add(sharedPreference?.getString(i, "") ?: "")
                    }
                    Log.d("TEST_OF_LOADING_DATA3", "Success  ${gen}         ")
                    genrAdapter.genr = gen
                }
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
//            dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//            })


        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
