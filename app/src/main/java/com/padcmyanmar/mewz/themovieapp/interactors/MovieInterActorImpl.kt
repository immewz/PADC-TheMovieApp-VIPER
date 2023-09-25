package com.padcmyanmar.mewz.themovieapp.interactors

import androidx.lifecycle.LiveData
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

object MovieInterActorImpl: MovieInterActor {

    // Model
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getNowPlayingMovies(onFailure)
    }

    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
       return mMovieModel.getPopularMovies(onFailure)
    }

    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        return mMovieModel.getTopRatedMovies(onFailure)
    }

    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieModel.getGenreList(onSuccess, onFailure)
    }

    override fun getMoviesByGenre(
        genreId: Int,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieModel.getMoviesByGenre(genreId, onSuccess, onFailure)
    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieModel.getActors(onSuccess, onFailure)
    }

    override fun getMovieDetail(movieId: Int, onFailure: (String) -> Unit): LiveData<MovieVO?>? {
        return mMovieModel.getMovieDetail(movieId, onFailure)
    }

    override fun getCreditsByMovie(
        movieId: Int,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        return mMovieModel.getCreditsByMovie(movieId, onSuccess, onFailure)
    }

    override fun getSearchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieModel.getSearchMovie(query)
    }
}