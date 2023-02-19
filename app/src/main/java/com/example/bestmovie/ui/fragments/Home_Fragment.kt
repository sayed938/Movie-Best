package com.example.bestmovie.ui.fragments

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovie.movies.PopularFragment
import com.example.bestmovie.movies.top_rated.TopRatedFragment
import com.example.bestmovie.movies.upcoming.UpcomingFragment
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.ui.ViewModel
import com.google.android.material.navigation.NavigationBarView


class Home_Fragment : Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var trending_adapter: HomeAdapter
    private lateinit var recycler_trending: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            com.example.bestmovie.R.layout.fragment_home_fragment,
            container,
            false
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColor(view)
        recycler_trending = view.findViewById(com.example.bestmovie.R.id.recycler_images_person)
        recycler_trending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler_trending.setHasFixedSize(true)
        viewModel = ViewModelProviders.of(this)[ViewModel::class.java]
        getTrending()
    }

    private fun getTrending() {
        viewModel.getMovieTrending()
        viewModel.liveDataMovietrending?.observe(viewLifecycleOwner, Observer { trendingList ->
            trending_adapter = HomeAdapter(trendingList as ArrayList<Result>)
            recycler_trending.adapter = trending_adapter
        })
    }

    private fun nav(f: Fragment) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.add(com.example.bestmovie.R.id.nav_host_movies, f)
        transaction.commit()

    }

    @SuppressLint("ResourceAsColor")
    private fun setColor(view: View) {
        view.findViewById<Button>(com.example.bestmovie.R.id.toprated_bt).setOnClickListener {
            nav(TopRatedFragment())
            view.findViewById<Button>(com.example.bestmovie.R.id.toprated_bt)
                .setTextColor(Color.parseColor("#0296E5"))
            view.findViewById<Button>(com.example.bestmovie.R.id.popular_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
            view.findViewById<Button>(com.example.bestmovie.R.id.upcoming_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
        }
        view.findViewById<Button>(com.example.bestmovie.R.id.upcoming_bt).setOnClickListener {
            nav(UpcomingFragment())
            view.findViewById<Button>(com.example.bestmovie.R.id.upcoming_bt)
                .setTextColor(Color.parseColor("#0296E5"))
            view.findViewById<Button>(com.example.bestmovie.R.id.popular_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
            view.findViewById<Button>(com.example.bestmovie.R.id.toprated_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
        }
        view.findViewById<Button>(com.example.bestmovie.R.id.popular_bt).setOnClickListener {
            nav(PopularFragment())
            view.findViewById<Button>(com.example.bestmovie.R.id.popular_bt)
                .setTextColor(Color.parseColor("#0296E5"))
            view.findViewById<Button>(com.example.bestmovie.R.id.toprated_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
            view.findViewById<Button>(com.example.bestmovie.R.id.upcoming_bt)
                .setTextColor(Color.parseColor("#FFFFFFFF"))
        }
    }

}