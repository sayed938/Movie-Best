package com.example.bestmovie.movies.top_rated

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

class TopRatedFragment : Fragment() {
    private lateinit var recyclerMovie: RecyclerView
    private lateinit var topMAdapter: MoviesAdapter
    private lateinit var topTVAdapter: TVAdapter
    private lateinit var recyclerTV: RecyclerView
    private lateinit var viewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovie = view.findViewById(R.id.recycler_top_rated_movie)
        recyclerTV = view.findViewById(R.id.recycler_top_rated_tv)
        recyclerTV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerTV.setHasFixedSize(true)
        recyclerMovie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMovie.setHasFixedSize(true)

        viewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        getMovies()
        getTV()
    }

    private fun getMovies() {
        viewModel.getMovie("top_rated")
        viewModel.liveDataMovie.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { o ->
                topMAdapter = MoviesAdapter(o as ArrayList<Result>)
                recyclerMovie.adapter = topMAdapter
            })
    }

    private fun getTV() {
        viewModel.getTV("tv/top_rated")
        viewModel.liveDataTv.observe(viewLifecycleOwner, Observer { o ->
            topTVAdapter = TVAdapter(o as ArrayList<PopularTVModel.ResultTVPopular>)
            recyclerTV.adapter = topTVAdapter
        })
    }

}