package com.example.bestmovie.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.ProjectData
import com.example.bestmovie.R
import com.squareup.picasso.Picasso

class HomeAdapter(var trending: ArrayList<com.example.bestmovie.pojo.Result>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.image_person_custom, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = trending.get(position)
        Picasso.get().load(ProjectData().imageSource+data.poster_path).into(holder.trend_img)
    }

    override fun getItemCount(): Int {
        return trending.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trend_img = itemView.findViewById<ImageView>(R.id.image_custom_person)
    }
}
