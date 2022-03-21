package com.ka.favcin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import com.ka.favcin.utils.api.ApiFactory
import com.ka.favcin.utils.api.ApiService
import com.ka.favcin.utils.pojo.Actor
import com.ka.favcin.utils.pojo.Casts
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.actor_item.view.*

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {
    var actors:List<Actor> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorAdapter.ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_item,parent,false)
        return ActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorAdapter.ActorViewHolder, position: Int) {
       val actor = actors[position]
        with(holder){
            name1.text=actor.name
            Picasso.get().load(ApiFactory.BASE_POSTER_URL + ApiService.SMALL_POSTER_SIZE +actor.profile_path).into(holder.photo)
        }
    }

    override fun getItemCount()=actors.size

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView.photoActor
        val name1 = itemView.nameActor
    }

}
