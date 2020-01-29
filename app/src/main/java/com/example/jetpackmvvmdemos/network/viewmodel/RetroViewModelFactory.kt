package com.example.jetpackmvvmdemos.network.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackmvvmdemos.MyRetroApplication
import com.example.jetpackmvvmdemos.network.di.APIComponent
import com.example.jetpackmvvmdemos.network.di.APIModule
import com.example.jetpackmvvmdemos.network.di.DaggerAPIComponent
import com.example.jetpackmvvmdemos.network.repository.APIURL
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import javax.inject.Inject

class RetroViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var retrofitRepository: RetrofitRepository
    @Inject
    lateinit var ctx: Context

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     //   initDaggerComponent()
       var apiComponent: APIComponent = MyRetroApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(RetroViewModel::class.java)) {
            return RetroViewModel(retrofitRepository,ctx) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    /*fun initDaggerComponent() {
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)
    }*/
}