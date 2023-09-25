package com.padcmyanmar.mewz.themovieapp.mvp.views

import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO

interface MovieDetailView: IBaseView {
    fun showMovieDetail(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}