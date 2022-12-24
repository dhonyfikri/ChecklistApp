package com.fikri.checklistapp.core.di

import com.fikri.checklistapp.core.data.AuthRepository
import com.fikri.checklistapp.core.data.ChecklistItemRepository
import com.fikri.checklistapp.core.data.ChecklistRepository
import com.fikri.checklistapp.core.data.source.remote.RemoteDataSource
import com.fikri.checklistapp.core.data.source.remote.network.ApiConfig
import com.fikri.checklistapp.core.domain.respository_interface.IAuthRepository
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistItemRepository
import com.fikri.checklistapp.core.domain.respository_interface.IChecklistRepository
import org.koin.dsl.module

object CoreModule {
    val networkModule = module {
        single { ApiConfig.getApiService() }
    }
    val repositoryModule = module {
        single { RemoteDataSource(get()) }
        single<IAuthRepository> { AuthRepository(get()) }
        single<IChecklistRepository> { ChecklistRepository(get()) }
        single<IChecklistItemRepository> { ChecklistItemRepository(get()) }
    }
}