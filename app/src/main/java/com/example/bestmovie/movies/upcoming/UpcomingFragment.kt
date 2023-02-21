package com.example.bestmovie.movies.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.R
import com.example.bestmovie.movies.MoviesAdapter
import com.example.bestmovie.movies.TVAdapter
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.ViewModel


class UpcomingFragment : Fragment() {
    private lateinit var recyclerMovie: RecyclerView
    private lateinit var upMAdapter: MoviesAdapter
    private lateinit var onAir: TVAdapter
    private lateinit var recyclerTV: RecyclerView
    private lateinit var viewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovie = view.findViewById(R.id.recycler_upcoming_movie)
        recyclerMovie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMovie.setHasFixedSize(true)
        recyclerTV = view.findViewById(R.id.recycler_on_air_tv)
        recyclerTV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerTV.setHasFixedSize(true)
        viewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        getMovie()
        getTV()
    }

    private fun getMovie() {
        viewModel.getMovie("upcoming")
        viewModel.liveDataMovie.observe(viewLifecycleOwner, Observer { o ->
            upMAdapter = MoviesAdapter(o as ArrayList<Result>)
            recyclerMovie.adapter = upMAdapter
        })
    }

    private fun getTV() {
        viewModel.getTV("trending/tv/day")
        viewModel.liveDataTv.observe(viewLifecycleOwner, Observer { o ->
            onAir = TVAdapter(o as ArrayList<PopularTVModel.ResultTVPopular>)
            recyclerTV.adapter = onAir
        })
    }

}