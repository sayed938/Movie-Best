package com.example.bestmovie.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bestmovie.R
import com.squareup.picasso.Picasso

class DetailsMovie : AppCompatActivity() {
    lateinit var   name_detail:TextView
    lateinit var overview_detail:TextView
    lateinit var image:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
        name_detail=findViewById(R.id.name_detail)
        overview_detail=findViewById(R.id.overview_detail)
        image=findViewById(R.id.image_detail)
        val namemovie: String? = intent.getStringExtra("name")
        val overviewmovie:String?=intent.getStringExtra("overview")
        name_detail.setText(namemovie)
        overview_detail.setText(overviewmovie)
        val img:String?=intent.getStringExtra("img")
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+img).into(image)
    }
}