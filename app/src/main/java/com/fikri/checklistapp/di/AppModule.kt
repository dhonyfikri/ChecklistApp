package com.fikri.checklistapp.di

import com.fikri.checklistapp.core.domain.use_case.*
import com.fikri.checklistapp.view_model.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<AuthUseCase> { AuthInteractor(get()) }
        factory<ChecklistUseCase> { ChecklistInteractor(get()) }
        factory<ChecklistItemUseCase> { ChecklistItemInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { CreateChecklistViewModel(get()) }
        viewModel { ChecklistItemListViewModel(get()) }
    }
}