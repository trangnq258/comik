package com.example.comik.di

import com.example.comik.ui.comic.ComicViewModel
import com.example.comik.ui.detail.DetailViewModel
import com.example.comik.ui.favorite.FavoriteViewModel
import com.example.comik.ui.home.HomeViewModel
import com.example.comik.ui.listcomic.ListComicViewModel
import com.example.comik.ui.seemore.SeeMoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { ComicViewModel(get()) }
    viewModel { SeeMoreViewModel(get(), get(), get(), get(), get()) }
    viewModel { ListComicViewModel(get()) }
    viewModel { DetailViewModel(get(), get(), get(), get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}
