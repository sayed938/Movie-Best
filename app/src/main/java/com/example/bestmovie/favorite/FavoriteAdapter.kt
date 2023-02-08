package com.example.bestmovie.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.R
import com.example.bestmovie.Room.DatabaseFavorite
import com.example.bestmovie.Room.MoviesModelBase
import com.example.bestmovie.Room.RoomOperations
import com.squareup.picasso.Picasso
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

var list: ArrayList<MoviesModelBase> = ArrayList<MoviesModelBase>()
class FavoriteAdapter():RecyclerView.Adapter<FavoriteAdapter.viewholder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.viewholder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)
        return viewholder(view)
    }
    override fun onBindViewHolder(holder: FavoriteAdapter.viewholder, position: Int) {
        val data = list[position]
        holder.namemovie.text = data?.name
        holder.datemovie.text = data?.date
        holder.imgdelete.setOnClickListener{
            holder.deleteMovie()
        }
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data?.img).into(holder.imgfavorite)
    }
    override fun getItemCount(): Int {
        return list!!.size
    }
    @JvmName("setList1")
    fun setList(listt: List<MoviesModelBase?>) {
        list = listt as ArrayList<MoviesModelBase>
        notifyDataSetChanged()
    }
    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview)
        {
        var imgfavorite=itemview.findViewById(R.id.imgfavor)as ImageView
        var datemovie = itemview.findViewById(R.id.favoritemoviedate)as TextView
        var namemovie = itemview.findViewById(R.id.favoritemovie)as TextView
        var imgdelete = itemview.findViewById(R.id.delete)as ImageView
        fun deleteMovie() {
            deletefromRoom()
            list.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
            notifyItemRangeChanged(adapterPosition, list.size)
        }
        private fun deletefromRoom(){
            val obj: MoviesModelBase= list[adapterPosition]
           val operate=RoomOperations(obj.idd,obj.name,obj.date,obj.img,itemView.context)
            operate.deleteMovie()
        }
    }
}

