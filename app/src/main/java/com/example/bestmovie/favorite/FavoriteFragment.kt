package com.example.bestmovie.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.R
import com.example.bestmovie.Room.DatabaseFavorite
import com.example.bestmovie.Room.MoviesModelBase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class FavoriteFragment : Fragment() {
    private var recycler:RecyclerView? =null
    private var adapter_favorite:FavoriteAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler=view.findViewById(R.id.favorite_recycler)
        adapter_favorite=FavoriteAdapter()
        recycler!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recycler!!.setHasFixedSize(true)
        recycler?.adapter=adapter_favorite
        getData(view)

    }
    fun getData(view : View){
        val databasee: DatabaseFavorite? = DatabaseFavorite.getInstance(view.context)
        databasee?.dao()?.getdata()?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<List<MoviesModelBase?>?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: List<MoviesModelBase?>) {
                    adapter_favorite?.setList(t)
                    adapter_favorite?.notifyDataSetChanged()
                }
                override fun onError(e: Throwable) {}
            })
    }


}