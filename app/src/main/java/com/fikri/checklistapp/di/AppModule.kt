package com.fikri.checklistapp.di

import com.fikri.checklistapp.core.domain.use_case.AuthInteractor
import com.fikri.checklistapp.core.domain.use_case.AuthUseCase
import com.fikri.checklistapp.core.domain.use_case.ChecklistInteractor
import com.fikri.checklistapp.core.domain.use_case.ChecklistUseCase
import com.fikri.checklistapp.view_model.CreateChecklistViewModel
import com.fikri.checklistapp.view_model.HomeViewModel
import com.fikri.checklistapp.view_model.MainViewModel
import com.fikri.checklistapp.view_model.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val useCaseModule = module {
        factory<AuthUseCase> { AuthInteractor(get()) }
        factory<ChecklistUseCase> { ChecklistInteractor(get()) }
    }

    val viewModelModule = module {
        viewModel { MainViewModel(get()) }
        viewModel { RegisterViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { CreateChecklistViewModel(get()) }
    }
}