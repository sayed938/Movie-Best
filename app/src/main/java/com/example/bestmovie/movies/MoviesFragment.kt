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
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.ViewModel

class BlankFragment : Fragment() {
    private var movieAdapter: MoviesAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var viewmodel: ViewModel?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_movie)
        recyclerView!!.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView!!.setHasFixedSize(true)
        viewmodel = ViewModelProviders.of(this)[ViewModel::class.java]
        viewmodel?.getmoviedata()
        viewmodel?.mutableLiveData?.observe(viewLifecycleOwner, Observer { movieList ->
            movieAdapter = MoviesAdapter(movieList as ArrayList<Result>?)
            recyclerView?.adapter = movieAdapter
        })
    }

}