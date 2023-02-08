package com.example.bestmovie.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class MoviesModelBase(@PrimaryKey var idd: Int,
                           var name: String, var date: String,
                           var img: String
)
