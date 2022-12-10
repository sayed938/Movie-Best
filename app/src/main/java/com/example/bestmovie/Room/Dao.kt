package com.example.bestmovie.Room


import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@androidx.room.Dao
open interface Dao {
    @Insert
    fun insert(post: MoviesModelBase?): Completable?

    @Query("select * from favorite_table")
    fun getdata(): Single<List<MoviesModelBase?>?>?
    @Query("SELECT * From favorite_table WHERE idd=:id")
    fun exist_s(id: Int): Single<MoviesModelBase>
    @Query("DELETE FROM favorite_table WHERE idd=:objmodel")
    fun delete(objmodel: Int):Completable?
}