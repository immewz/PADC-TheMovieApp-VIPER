package com.padcmyanmar.mewz.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.interactors.MovieInterActor
import com.padcmyanmar.mewz.themovieapp.interactors.MovieInterActorImpl
import com.padcmyanmar.mewz.themovieapp.mvp.views.MainView

class MainPresenterImpl: ViewModel(), MainPresenter {

    var mView: MainView? = null

    // InterActor
    private var mMovieInterActor: MovieInterActor = MovieInterActorImpl

    private var mGenre: List<GenreVO>? = null

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mMovieInterActor.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        mMovieInterActor.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }

        mMovieInterActor.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        mMovieInterActor.getGenreList(
            onSuccess = {
                mGenre = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let {genreId ->
                    onTapGenre(genreId)
                }
            }, onFailure = { mView?.showError(it) }
        )

        mMovieInterActor.getActors(
            onSuccess = { mView?.showActors(it) },
            onFailure = { mView?.showError(it) }
        )
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailsMovie(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailsMovie(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailsMovie(movieId)
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenre?.getOrNull(genrePosition)?.id?.let {genreId ->
            mMovieInterActor.getMoviesByGenre(genreId,
                onSuccess = { mView?.shoMoviesByGenre(it)},
                onFailure = { mView?.showError(it)} )
        }
    }
}