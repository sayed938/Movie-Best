package com.example.bestmovie.movies


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.R
import com.example.bestmovie.Room.DatabaseFavorite
import com.example.bestmovie.Room.MoviesModelBase
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.DetailsMovie
import com.squareup.picasso.Picasso
import io.reactivex.CompletableObserver
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
        holder.rate.setText(""+data?.vote_average)
        holder.setfavorite()

    }

    override fun getItemCount(): Int {
        return list!!.size
    }
    inner class viewHolder(itemview: View):RecyclerView.ViewHolder(itemview),View.OnClickListener {
        val moviename: TextView = itemView.findViewById(R.id.movie_name)
        val imgmoview: ImageView = itemView.findViewById(R.id.movie_image)
        val img_favor: ImageView = itemView.findViewById(R.id.img_favorite)
        val rate: TextView = itemView.findViewById(R.id.viewrate)
        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(
            itemview.resources,
            R.drawable.ic_favorite_filled, null
        )

        fun setfavorite() {
            val data: com.example.bestmovie.pojo.Result
            data = list!!.get(adapterPosition)
            val database: DatabaseFavorite? = DatabaseFavorite.getInstance(itemView.context)
            database?.dao()?.exist_s(data.id)?.subscribeOn(Schedulers.computation())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : SingleObserver<MoviesModelBase?> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(t: MoviesModelBase) {
                        img_favor.setImageDrawable(icFavoriteFilledImage)
                    }

                    override fun onError(e: Throwable) {
                    }

                })

        }

//Insert To Room Database
        override fun onClick(p0: View?) {
            val obj: Result
            obj = list!!.get(adapterPosition)
            if (p0!!.id == R.id.img_favorite) {

                    val database: DatabaseFavorite? = DatabaseFavorite.getInstance(p0.context)
                    database?.dao()!!
                        .insert(MoviesModelBase(obj.id, obj.original_title, obj.release_date,obj.poster_path))
                        ?.subscribeOn(Schedulers.io())?.subscribe(object : CompletableObserver {
                            override fun onSubscribe(d: Disposable) {}
                            override fun onComplete() {

                            }

                            override fun onError(e: Throwable) {
                                println(e.message)

                            }
                        })
                    img_favor.setImageDrawable(icFavoriteFilledImage)
                }
    else {
                var intent:Intent = Intent(p0.context,DetailsMovie::class.java)
                intent.putExtra("img",obj.poster_path)
                intent.putExtra("name",obj.original_title)
                intent.putExtra("overview",obj.overview)
                p0.context.startActivity(intent)
            }
            }


        }
    }









