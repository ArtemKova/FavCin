package com.ka.favcin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ka.favcin.R
import kotlinx.android.synthetic.main.genr_item.view.*

class GenrAdapter: RecyclerView.Adapter<GenrAdapter.GenrViewHolder>() {
    var genr:List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenrAdapter.GenrViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genr_item,parent,false)
        return GenrViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenrAdapter.GenrViewHolder, position: Int) {
        val actor = genr[position]
        with(holder){
            nameGenr1.text=actor
                    }
    }

    override fun getItemCount()=genr.size

    inner class GenrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameGenr1 = itemView.nameGenr1
    }

}