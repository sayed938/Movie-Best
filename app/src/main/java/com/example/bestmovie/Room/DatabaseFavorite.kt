package com.example.bestmovie.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MoviesModelBase::class], version = 6)
abstract class DatabaseFavorite: RoomDatabase() {

    abstract fun dao(): Dao?
    companion object {
        private var instance: DatabaseFavorite? = null

        @Synchronized
        fun getInstance(context: Context?): DatabaseFavorite? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!,
                    DatabaseFavorite::class.java, "instance_data"
                )
                    .fallbackToDestructiveMigration().build()
            }
            return instance
        }

    }
}