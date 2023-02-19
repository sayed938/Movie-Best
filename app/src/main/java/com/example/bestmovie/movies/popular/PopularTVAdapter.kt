package com.example.bestmovie.movies.popular

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.ProjectData
import com.example.bestmovie.R
import com.example.bestmovie.Room.DatabaseFavorite
import com.example.bestmovie.Room.MoviesModelBase
import com.example.bestmovie.Room.RoomOperations
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import com.squareup.picasso.Picasso
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PopularTVAdapter(var tvList: ArrayList<PopularTVModel.ResultTVPopular>) :
    RecyclerView.Adapter<PopularTVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = tvList.get(position)
        holder.moviename.text = data.name
        Picasso.get().load(ProjectData().imageSource + data.backdrop_path)
            .into(holder.imgmoview)
        holder.stillFavoriteTV()
        holder.imgfavor.setOnClickListener {
            val operate = RoomOperations(
                data.id,
                data.name!!,
                data.first_air_date!!,
                data.poster_path!!,
                holder.context()
            )
            operate.insertMovie()
            holder.imgfavor.setImageResource(R.drawable.ic_favorite_filled)
        }

    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moviename = itemView.findViewById(R.id.movie_name) as TextView
        val imgmoview = itemView.findViewById(R.id.movie_image) as ImageView
        val imgfavor = itemView.findViewById(R.id.img_favorite) as ImageView
        fun context(): Context {
            return itemView.context
        }

        fun stillFavoriteTV() {
            val data: PopularTVModel.ResultTVPopular = tvList[adapterPosition]
            val database = DatabaseFavorite.getInstance(itemView.context) as DatabaseFavorite
            database.dao()?.exist_s(data.id)?.subscribeOn(Schedulers.io())
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
