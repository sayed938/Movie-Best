package com.example.bestmovie.movies


import android.content.Context
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
import com.example.bestmovie.pojo.Result
import com.squareup.picasso.Picasso
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MoviesAdapter( var list: ArrayList<Result>?):RecyclerView.Adapter<MoviesAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        return viewHolder(view)
    }
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data=list?.get(position)
        holder.moviename.text=data?.original_title
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data?.backdrop_path).into(holder.imgmoview)
        holder.rate.text = data?.vote_average.toString()
        holder.stillFavorite()
        
        holder.imgfavor.setOnClickListener{
            val operate=RoomOperations(data!!.id, data.original_title, data.release_date,
                data.poster_path,holder.context())
            operate.insertMovie()
            holder.imgfavor.setImageResource(R.drawable.ic_favorite_filled)
        }

    }
    override fun getItemCount(): Int {
        return list!!.size
    }
    inner class viewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
            val moviename= itemView.findViewById(R.id.movie_name) as TextView
            val imgmoview= itemView.findViewById(R.id.movie_image)as ImageView
            val imgfavor= itemView.findViewById(R.id.img_favorite) as ImageView
            val rate = itemView.findViewById(R.id.viewrate) as TextView
        fun context():Context{
            return itemView.context
        }
        fun stillFavorite() {
                val data: Result = list!![adapterPosition]
                val database= DatabaseFavorite.getInstance(itemView.context)as DatabaseFavorite
                database.dao()?.exist_s(data.id)?.subscribeOn(Schedulers.computation())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(object : SingleObserver<MoviesModelBase?> {
                        override fun onSubscribe(d: Disposable) {
                        }
                        override fun onSuccess(t: MoviesModelBase) {
                            imgfavor.setImageResource(R.drawable.ic_favorite_filled)
                        }
                        override fun onError(e: Throwable) {
                        }
                    })
            }
            }
    }