package com.padcmyanmar.mewz.themovieapp.routers

import android.app.Activity
import com.padcmyanmar.mewz.themovieapp.activities.MovieDetailActivity
import com.padcmyanmar.mewz.themovieapp.activities.SearchActivity

fun Activity.navigateToMovieDetailsActivity(movieId: Int){
    startActivity(MovieDetailActivity.newIntent(this, movieId))
}

fun  Activity.navigateToMovieSearchActivity(){
    startActivity(SearchActivity.newIntent(this))
}