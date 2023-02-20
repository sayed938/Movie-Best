package com.example.bestmovie.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.R
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.ViewModel

class PopularFragment : Fragment() {
    private lateinit var movieAdapter: MoviesAdapter
    private lateinit var recyclerMovie: RecyclerView
    private lateinit var recyclerTV: RecyclerView
    private lateinit var viewmodel: ViewModel
    private lateinit var tvAdapter: TVAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerMovie = view.findViewById(R.id.recycler_popular_movie)
        recyclerTV = view.findViewById(R.id.recycler_popular_tv)
        recyclerMovie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMovie.setHasFixedSize(true)
        recyclerTV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerTV.setHasFixedSize(true)
        viewmodel = ViewModelProviders.of(this)[ViewModel::class.java]
        getTV(recyclerTV)
        getMovies(recyclerMovie)

    }

     fun getMovies(recyclerView: RecyclerView) {
        viewmodel.getMovie("popular")
        viewmodel.liveDataMovie.observe(viewLifecycleOwner, Observer { movieList ->
            movieAdapter = MoviesAdapter(movieList as ArrayList<Result>?)
            recyclerView.adapter = movieAdapter
        })
    }

     fun getTV(recyclerView: RecyclerView) {
        viewmodel.getTV("tv/popular")
        viewmodel.liveDataTv.observe(viewLifecycleOwner, Observer { tvlist ->
            tvAdapter = TVAdapter(tvlist as ArrayList<PopularTVModel.ResultTVPopular>)
            recyclerView.adapter = tvAdapter

        })
    }
}