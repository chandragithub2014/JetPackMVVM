package com.example.jetpackmvvmdemos.network.di


import android.content.Context
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class APIModule constructor(baseURL: String,ctx:Context) {
    var baseURL: String? = ""
    var ctx: Context

    init {
        this.baseURL = baseURL
        this.ctx = ctx
    }

    @Singleton
    @Provides
    fun getContext():Context{
        return  ctx;
    }
@Singleton
@Provides
fun provideOKHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
         .readTimeout(1200, TimeUnit.SECONDS)
         .connectTimeout(1200, TimeUnit.SECONDS)
         .build()
    }
@Singleton
@Provides
fun provideGSON(): GsonConverterFactory {

    return GsonConverterFactory.create()
    }
@Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {

      return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                 .build()
 }

    @Provides
    fun provideRetroRepository(): RetrofitRepository {
        return RetrofitRepository()
    }


}