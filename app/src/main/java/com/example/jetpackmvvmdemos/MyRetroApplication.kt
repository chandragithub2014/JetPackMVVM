package com.example.jetpackmvvmdemos

import android.app.Application
import android.content.Context
import com.example.jetpackmvvmdemos.network.di.APIComponent
import com.example.jetpackmvvmdemos.network.di.APIModule
import com.example.jetpackmvvmdemos.network.di.DaggerAPIComponent
import com.example.jetpackmvvmdemos.network.repository.APIURL


class MyRetroApplication : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()
    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL, applicationContext))
            .build()
        return apiComponent
    }
}