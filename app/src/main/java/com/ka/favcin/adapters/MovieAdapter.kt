package com.ka.favcin.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.utils.api.ApiFactory
import com.ka.core.data.api.ApiService
import com.ka.core.data.db.Results
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    var movies: List<com.ka.core.data.db.Results> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private lateinit var context: Context
    private var recyclerViewGenres: RecyclerView? = null
    private var onPosterClickListener: OnPosterClickListener? = null
    private var onReachEndListener: OnReachEndListener? = null

    //    val sharedPreference =
//        context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    interface OnPosterClickListener {
        fun onPosterClick(position: Int, id: Int)
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }


    fun setOnPosterClickListener(onPosterClickListener: OnPosterClickListener?) {
        this.onPosterClickListener = onPosterClickListener
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener?) {
        this.onReachEndListener = onReachEndListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        context = parent.context

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movies.size >= 20 && position == movies.size - 4 && onPosterClickListener != null) {
            onReachEndListener!!.onReachEnd()
        }
//        val sharedPreference =
//            context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
//        val genrAdapter = GenrAdapter()
        val movie: com.ka.core.data.db.Results = movies[position]
//        var genre = movie.genreIds
//        Log.d("TEST_OF_LOADING_DATA3", "Success  ${genre}         ")
//        if (genre != null) {
//            var gen: MutableList<String> = mutableListOf()
//            for (i in genre) {
//                gen.add(sharedPreference?.getString(i, "") ?: "")
//            }
//            Log.d("TEST_OF_LOADING_DATA3", "Success  ${gen}         ")
//            genrAdapter.genr = gen
//        }
holder.textViewRaiting.text= movie.voteAverage.toString()
        Log.d("TEST_OF_DATA", "$movie /n")
        Picasso.get()
            .load(ApiFactory.BASE_POSTER_URL + com.ka.core.data.api.ApiService.SMALL_POSTER_SIZE + movie.posterPath)
            .into(holder.imageViewSmallPoster)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setOnPosterClickListener() {
        TODO("Not yet implemented")
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //        private var recyclerViewGenres: RecyclerView? = null
//        init {
//            recyclerViewGenres = itemView.findViewById(R.id.genrFirst)
//            recyclerViewGenres!!.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        }


        val imageViewSmallPoster: ImageView
val  textViewRaiting:TextView
        init {
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster)
            itemView.setOnClickListener {
                if (onPosterClickListener != null) {
                    movies[adapterPosition].id
                        ?.let { it1 -> onPosterClickListener!!.onPosterClick(adapterPosition, it1) }


                }
            }
            textViewRaiting= itemView.findViewById(R.id.textViewRaiting)
        }
    }

//    fun clear() {
//        movies.clear()
//
//        notifyDataSetChanged()
//    }

//    fun setMovies(moviess: MutableList<Results>) {
//        this.moviess = moviess
//        notifyDataSetChanged()
//    }

//    fun addMovies(movies: List<Results>?) {
//        this.movies.addAll(movies!!)
//        notifyDataSetChanged()
//    }

//    fun getMovies(): List<Results> {
//        return movies
//    }

//    init {
//        movies = ArrayList<Results>()
//    }

}