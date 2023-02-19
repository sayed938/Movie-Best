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
import com.example.bestmovie.movies.popular.PopularMoviesAdapter
import com.example.bestmovie.movies.popular.PopularTVAdapter
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.ViewModel

class PopularFragment : Fragment() {
    private var movieAdapter: PopularMoviesAdapter? = null
    private var recyclerMovie: RecyclerView? = null
    private var recyclerTV: RecyclerView? = null
    private var viewmodel: ViewModel? = null
    private var tvAdapter: PopularTVAdapter? = null
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
        recyclerMovie!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerMovie!!.setHasFixedSize(true)
        recyclerTV!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerTV!!.setHasFixedSize(true)
        viewmodel = ViewModelProviders.of(this)[ViewModel::class.java]
        getTV()
        getMovies()

    }

    private fun getMovies() {
        viewmodel?.getMoviePopular()
        viewmodel?.liveDataMoviepopular?.observe(viewLifecycleOwner, Observer { movieList ->
            movieAdapter = PopularMoviesAdapter(movieList as ArrayList<Result>?)
            recyclerMovie?.adapter = movieAdapter
        })
    }

    private fun getTV() {
        viewmodel?.getTVPopular()
        viewmodel?.liveDataTvpopular?.observe(viewLifecycleOwner, Observer { tvlist ->
            tvAdapter = PopularTVAdapter(tvlist as ArrayList<PopularTVModel.ResultTVPopular>)
            recyclerTV?.adapter = tvAdapter
            println(tvlist[0].first_air_date)
        })
    }
}