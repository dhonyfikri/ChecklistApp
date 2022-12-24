package com.fikri.checklistapp.di

import com.fikri.checklistapp.core.domain.use_case.AuthInteractor
import com.fikri.checklistapp.core.domain.use_case.AuthUseCase
import com.fikri.checklistapp.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<AuthUseCase> { AuthInteractor(get()) }
//        factory<GenreUseCase> { GenreInteractor(get()) }
//        factory<MovieUseCase> { MovieInteractor(get()) }
//        factory<VideoUseCase> { VideoInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
//        viewModel { SearchMovieViewModel(get(), get()) }
//        viewModel { GenreDiscoverViewModel(get(), get()) }
    }
}