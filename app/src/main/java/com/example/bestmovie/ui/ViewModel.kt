package com.example.bestmovie.ui


import android.annotation.SuppressLint
import com.example.bestmovie.ProjectData
import androidx.lifecycle.MutableLiveData
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.pojo.TrendingPerson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ViewModel : androidx.lifecycle.ViewModel() {
    val liveDataMovie: MutableLiveData<List<Result>> =
        MutableLiveData<List<Result>>()
    val liveDataMovietrending: MutableLiveData<List<TrendingPerson.Result>> =
        MutableLiveData<List<TrendingPerson.Result>>()
    val liveDataTv: MutableLiveData<List<PopularTVModel.ResultTVPopular>> =
        MutableLiveData<List<PopularTVModel.ResultTVPopular>>()

    @SuppressLint("CheckResult")
    fun getMovie(path: String) {
        val observable =
            RetrofitBuilder().interfaceObj().getMovie("movie/" + path, ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe { o ->
            liveDataMovie.setValue(o.results)

        }
    }

    @SuppressLint("CheckResult")
    fun getTV(path: String) {
        val observable =
            RetrofitBuilder().interfaceObj().getTv("3/"+path, ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe { o -> liveDataTv.setValue(o.results) }
    }

    @SuppressLint("CheckResult")
    fun getMovieTrending() {
        val observable =
            RetrofitBuilder().interfaceObj().getTrendingPerson("trending/person/day", ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe { o -> liveDataMovietrending.setValue(o.results) }
    }
}


