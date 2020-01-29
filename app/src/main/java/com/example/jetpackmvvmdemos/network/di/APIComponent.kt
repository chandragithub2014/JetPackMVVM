package com.example.jetpackmvvmdemos.network.di

import com.example.jetpackmvvmdemos.AppModule
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import com.example.jetpackmvvmdemos.network.view.RetroFragment
import com.example.jetpackmvvmdemos.network.viewmodel.RetroViewModel
import com.example.jetpackmvvmdemos.network.viewmodel.RetroViewModelFactory
import com.example.jetpackmvvmdemos.workmanager.view.WorkManagerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface APIComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(retroViewModel: RetroViewModel)
    fun inject(retroFragment: RetroFragment)
    fun inject(retroViewModelFactory: RetroViewModelFactory)
    fun inject(workManagerFragment: WorkManagerFragment)
}