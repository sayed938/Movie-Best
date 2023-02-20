package com.example.bestmovie.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bestmovie.ProjectData
import com.example.bestmovie.R
import com.squareup.picasso.Picasso

class DetailsMovie : AppCompatActivity() {
    lateinit var name_detail: TextView
    lateinit var overview_detail: TextView
    lateinit var datee: TextView
    lateinit var ratee: TextView
    lateinit var image: ImageView
    lateinit var img_profile: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movie)
        ratee = findViewById(R.id.details_rate)
        datee = findViewById(R.id.details_date)
        name_detail = findViewById(R.id.detail_name)
        overview_detail = findViewById(R.id.details_about)
        img_profile = findViewById(R.id.detail_profile)
        image = findViewById(R.id.image_detail)
        val bundle: Bundle? = intent.extras
        Picasso.get().load(ProjectData().imageSource + bundle?.get("profile")).into(img_profile)
        Picasso.get().load(ProjectData().imageSource + bundle?.get("details_img")).into(image)
        name_detail.text = bundle?.get("name").toString()
        overview_detail.text =  bundle?.get("details").toString()
        ratee.text = (bundle?.get("rate").toString())
        datee.text = bundle?.get("date").toString()
    }
}