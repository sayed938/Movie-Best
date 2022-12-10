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
        holder.deletefavorit()
        holder.postposition(position)
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

    inner class viewholder(itemview: View) : RecyclerView.ViewHolder(itemview),
        View.OnClickListener {
        var imgfavorite=itemview.findViewById<ImageView>(R.id.imgfavor)
        var datemovie = itemview.findViewById<TextView>(R.id.favoritemoviedate)
        var namemovie = itemview.findViewById<TextView>(R.id.favoritemovie)
        var imgdelete = itemview.findViewById<ImageView>(R.id.delete)
        var currentPos: Int = -1

        fun postposition(position: Int) {
            this.currentPos = position
        }

        fun deletefavorit() {
            imgdelete.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (p0!!.id == R.id.delete) {
               deleteeitem()
            } else println("noooo")
        }

        ////////////Delete From Recycler//////////////
        fun deleteeitem() {
            val objmodel: MoviesModelBase
            objmodel = list.get(adapterPosition)
            deletefromRoom(objmodel.idd)
            list.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
            notifyItemRangeChanged(adapterPosition, list.size)

        }

        ////////////Delete From Room Database///////////
        fun deletefromRoom(id:Int){
            var dataaabase = DatabaseFavorite.getInstance(itemView.context)
            dataaabase?.dao()?.delete(id)?.subscribeOn(Schedulers.computation())
                ?.subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        }
    }
}

