package com.example.bestmovie.Room

import android.content.Context
import android.view.View
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

data class RoomOperations (
         var Id:Int,
         var title:String,
         var date:String,
         var poster:String,
         var context:Context){
    //Insertion
    fun insertMovie(){
        val database: DatabaseFavorite? = DatabaseFavorite.getInstance(context)
        database?.dao()!!
            .insert(MoviesModelBase(Id,title,date,poster))?.subscribeOn(Schedulers.io())?.subscribe(object :CompletableObserver{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                }

            })
    }
    //Deletion
    fun deleteMovie(){
        val database: DatabaseFavorite? = DatabaseFavorite.getInstance(context)
        database?.dao()!!
            .delete(Id)?.subscribeOn(Schedulers.computation())?.subscribe(object :CompletableObserver{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }
                override fun onError(e: Throwable) {
                }

            })
    }
    fun getData(){
        val databasee: DatabaseFavorite? = DatabaseFavorite.getInstance(context)
        databasee?.dao()?.getdata()?.subscribeOn(Schedulers.computation())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<List<MoviesModelBase?>?> {
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: List<MoviesModelBase?>) {}
                override fun onError(e: Throwable) {}
            })
    }
}



