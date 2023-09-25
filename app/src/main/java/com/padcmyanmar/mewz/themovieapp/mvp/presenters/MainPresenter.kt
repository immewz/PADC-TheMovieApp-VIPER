package com.padcmyanmar.mewz.themovieapp.mvp.presenters

import com.padcmyanmar.mewz.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate, MovieViewHolderDelegate,
    ShowcaseViewHolderDelegate {
        fun initView(view: MainView)
        fun onTapGenre(genrePosition: Int)
}