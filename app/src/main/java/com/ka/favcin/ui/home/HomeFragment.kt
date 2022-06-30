package com.ka.favcin.ui.home

import android.content.Context
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
import com.ka.favcin.adapters.MovieAdapter.OnPosterClickListener
import com.ka.favcin.databinding.FragmentHomeBinding
import com.ka.favcin.ui.FanApp
import com.ka.favcin.ui.MAIN
import com.ka.favcin.ui.ViewModelFactory
import javax.inject.Inject


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private var recyclerViewPosters: RecyclerView? = null



    private val component by lazy {
        (requireActivity().application as FanApp).component
    }
//    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        homeViewModel =
            ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]


        val movieAdapter = MovieAdapter()

        val textView: TextView = binding.root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        recyclerViewPosters = binding.root.findViewById(R.id.recyclerViewPosters)
        recyclerViewPosters!!.layoutManager = GridLayoutManager(context, 2)
        recyclerViewPosters!!.adapter = movieAdapter
        homeViewModel.filmList.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = (it)
        })
        movieAdapter.setOnPosterClickListener(onPosterClickListener = object :
            OnPosterClickListener {
            override fun onPosterClick(position: Int, id: Int) {
                Log.d("ButtoPicture", "Кнопка сработала1 $position    $id")
                val bundle = Bundle()
                bundle.putInt("id", id)
                MAIN.navController.navigate(
                    R.id.action_navigation_home_to_navigation_dashboard,
                    bundle
                )

//                fun onPosterClick(position: Int) {
//
//
//                    Log.d("ButtoPicture", "Кнопка сработала2 $position")
////            val movie: Movie = movieAdapter.getMovies().get(position)
////            val intent = Intent(this@MainActivity, DetailActivity::class.java)
////            intent.putExtra("id", movie.getId())
////            startActivity(intent)
//                }
            }
        })

//        val disposable = ApiFactory.apiService.getMoviesFromApi()
//            .map { it.results }
////            .flatMap {ApiFactory.apiService1.getLittlePostersFromApi(posterPath = it.toString())}
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                var movies: MutableList<Results> = it as MutableList<Results>
//
//
//                movieAdapter?.setMovies(movies)
//                recyclerViewPosters!!.setAdapter(movieAdapter)
//
//                Log.d("TEST_OF_LOADING_DATA", "Success  ${movies}")
//            }, {
//
//            })
//        compositeDisposable.add(disposable)

//        movieAdapter.setOnPosterClickListener(MovieAdapter.OnPosterClickListener() {
//            fun onPosterClick(position: Int) {
//                val movie: Movie = movieAdapter.getMovies().get(position)
//                val intent = Intent(this@MainActivity, DetailActivity::class.java)
//                intent.putExtra("id", movie.getId())
//                startActivity(intent)
//            }
//        })


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
//        compositeDisposable.dispose()
    }


}



