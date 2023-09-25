package com.padcmyanmar.mewz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.interactors.MovieInterActor
import com.padcmyanmar.mewz.themovieapp.interactors.MovieInterActorImpl
import com.padcmyanmar.mewz.themovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter {

    var mView: MovieDetailView? = null

    // InterActor
    private var mMovieInterActor: MovieInterActor = MovieInterActorImpl

    override fun initView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
        mMovieInterActor.getMovieDetail(movieId){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetail(it)
            }
        }

        mMovieInterActor.getCreditsByMovie(movieId,
            onSuccess = { mView?.showCreditsByMovie(cast = it.first, crew =  it.second)},
            onFailure = { mView?.showError(it) })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {}
}