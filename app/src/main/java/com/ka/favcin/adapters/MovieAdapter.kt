package com.ka.favcin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.data.Movie
import com.squareup.picasso.Picasso

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movies: MutableList<Movie>
    private var onPosterClickListener: OnPosterClickListener? = null
    private var onReachEndListener: OnReachEndListener? = null

    interface OnPosterClickListener {
        fun onPosterClick(position: Int)
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
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (movies.size >= 20 && position == movies.size - 4 && onPosterClickListener != null) {
            onReachEndListener!!.onReachEnd()
        }
        val movie: Movie = movies[position]
        Picasso.get().load(movie.getPosterPath()).into(holder.imageViewSmallPoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageViewSmallPoster: ImageView

        init {
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster)
            itemView.setOnClickListener {
                if (onPosterClickListener != null) {
                    onPosterClickListener!!.onPosterClick(adapterPosition)
                }
            }
        }
    }

    fun clear() {
        movies.clear()
        notifyDataSetChanged()
    }

    fun setMovies(movies: MutableList<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun addMovies(movies: List<Movie>?) {
        this.movies.addAll(movies!!)
        notifyDataSetChanged()
    }

    fun getMovies(): List<Movie> {
        return movies
    }

    init {
        movies = ArrayList<Movie>()
    }

}