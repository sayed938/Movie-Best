package com.example.bestmovie.pojo

class TrendingPerson {
    class KnownFor {
        var adult = false
        var backdrop_path: String? = null
        var id = 0
        var title: String? = null
        var original_language: String? = null
        var original_title: String? = null
        var overview: String? = null
        var poster_path: String? = null
        var media_type: String? = null
        var genre_ids: ArrayList<Int>? = null
        var popularity = 0.0
        var release_date: String? = null
        var video = false
        var vote_average = 0.0
        var vote_count = 0
    }

    class Result {
        var adult = false
        var id = 0
        var name: String? = null
        var original_name: String? = null
        var media_type: String? = null
        var popularity = 0.0
        var gender = 0
        var known_for_department: String? = null
        var profile_path: String? = null
        var known_for: ArrayList<KnownFor>? = null
    }

    class Root {
        var page = 0
        var results: ArrayList<Result>? = null
        var total_pages = 0
        var total_results = 0
    }
}