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
import com.ka.favcin.R
import com.ka.favcin.databinding.FragmentDashboardBinding
import com.ka.favcin.databinding.FragmentHomeBinding
import com.ka.favcin.ui.MAIN
import com.ka.favcin.ui.home.HomeViewModel
import com.ka.favcin.utils.api.ApiFactory
import com.ka.favcin.utils.api.ApiService
import com.ka.favcin.utils.pojo.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var homeViewModel: HomeViewModel

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
        Log.d("ButtoPicture", "Кнопка сработала3 $id  ")
        lateinit var movie: Results
        if (id != null) {
            homeViewModel.getDetailInfo(id).observe(viewLifecycleOwner, Observer { it ->
                movie = it[0]
//                val mov = movie.map{it}
                Log.d("ButtoPicture", "Кнопка сработала4 $movie  ")
                Picasso.get().load(ApiFactory.BASE_POSTER_URL + ApiService.BIG_POSTER_SIZE +movie.getPosterPath()).into(bigPosterView)
                name_movie.text = movie.getTitle()
                describe_movie.text = movie.getOverview()
ratingBar.rating = movie.getVoteAverage()?.toFloat() ?: 3f
            })



//        val textView: TextView = binding.root.findViewById(R.id.text_dashboard)
            dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
            })
        }

            return binding.root
        }}
