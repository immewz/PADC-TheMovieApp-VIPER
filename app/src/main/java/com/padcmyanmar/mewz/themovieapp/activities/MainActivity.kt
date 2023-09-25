package com.padcmyanmar.mewz.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.padcmyanmar.mewz.themovieapp.R
import com.padcmyanmar.mewz.themovieapp.adapters.BannerAdapter
import com.padcmyanmar.mewz.themovieapp.adapters.ShowCaseAdapter
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModel
import com.padcmyanmar.mewz.themovieapp.data.models.MovieModelImpl
import com.padcmyanmar.mewz.themovieapp.data.vos.ActorVO
import com.padcmyanmar.mewz.themovieapp.data.vos.GenreVO
import com.padcmyanmar.mewz.themovieapp.data.vos.MovieVO
import com.padcmyanmar.mewz.themovieapp.databinding.ActivityMainBinding
import com.padcmyanmar.mewz.themovieapp.delegates.BannerViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.MovieViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.delegates.ShowcaseViewHolderDelegate
import com.padcmyanmar.mewz.themovieapp.mvp.presenters.MainPresenter
import com.padcmyanmar.mewz.themovieapp.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.mewz.themovieapp.mvp.views.MainView
import com.padcmyanmar.mewz.themovieapp.routers.navigateToMovieDetailsActivity
import com.padcmyanmar.mewz.themovieapp.routers.navigateToMovieSearchActivity
import com.padcmyanmar.mewz.themovieapp.utils.dummyGenreList
import com.padcmyanmar.mewz.themovieapp.viewpods.ActorListViewPod
import com.padcmyanmar.mewz.themovieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(), MainView{

    private lateinit var binding: ActivityMainBinding

    private lateinit var mBannerAdapter: BannerAdapter
    private lateinit var mShowcaseAdapter: ShowCaseAdapter

    private lateinit var mBestPopularMoviesListViewPod: MovieListViewPod
    private lateinit var mMoviesByGenreViewPod: MovieListViewPod
    private lateinit var mActorListViewPod: ActorListViewPod

    // Presenter
    private lateinit var mPresenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpToolbar()
        setUpViewPod()
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()

        setUpListeners()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpViewPod() {
        mBestPopularMoviesListViewPod = binding.vpBestPopularMoviesList.root
        mBestPopularMoviesListViewPod.setUpMovieListViewPod(mPresenter)

        mMoviesByGenreViewPod = binding.vpMoviesByGenre.root
        mMoviesByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorListViewPod = binding.vpActorList.root
//        mActorListViewPod.setUpActorViewPod("","","")
    }


    private fun setUpListeners() {
        binding.tabLayoutGenre.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mPresenter.onTapGenre(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }

    private fun setUpShowCaseRecyclerView() {
        mShowcaseAdapter = ShowCaseAdapter(mPresenter)
        binding.rvShowcases.adapter = mShowcaseAdapter
        binding.rvShowcases.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            binding.tabLayoutGenre.newTab().apply {
                text = it.name
                binding.tabLayoutGenre.addTab(this)
            }
        }
    }


    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(mPresenter)
        binding.viewPagerBanner.adapter = mBannerAdapter

        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.searchMovie-> {
                navigateToMovieSearchActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>) {
        mBannerAdapter.setNewData(nowPlayingMovies)
    }

    override fun showPopularMovies(popularMovies: List<MovieVO>) {
        mBestPopularMoviesListViewPod.setData(popularMovies)
    }

    override fun showTopRatedMovies(topRatedMovies: List<MovieVO>) {
        mShowcaseAdapter.setNewData(topRatedMovies)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpGenreTabLayout(genreList)
    }

    override fun shoMoviesByGenre(moviesByGenre: List<MovieVO>) {
        mMoviesByGenreViewPod.setData(moviesByGenre)
    }

    override fun showActors(actors: List<ActorVO>) {
        mActorListViewPod.setData(actors)
    }

    override fun navigateToMovieDetailsMovie(movieId: Int) {
        navigateToMovieDetailsActivity(movieId)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }


}