package com.padcmyanmar.mewz.themovieapp.mvp.views

import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO

interface MainView: IBaseView {
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun shoMoviesByGenre(moviesByGenre: List<MovieVO>)
    fun showActors(actors: List<ActorVO>)
    fun navigateToMovieDetailsMovie(movieId: Int)
}