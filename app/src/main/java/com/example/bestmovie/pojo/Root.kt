package com.example.bestmovie.pojo

data class Root( var page:Int,
                 var results: ArrayList<Result> ,
                 var total_pages:Int,
                 var total_results:Int) {
    fun getResults(): List<com.example.bestmovie.pojo.Result?>? {
        return results
    }
}