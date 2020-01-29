package com.example.jetpackmvvmdemos.network.viewmodel

import android.content.Context
import androidx.lifecycle.*
import androidx.work.*
import com.example.jetpackmvvmdemos.MyRetroApplication
import com.example.jetpackmvvmdemos.MyRetroApplication.Companion.ctx
import com.example.jetpackmvvmdemos.dataBindingRoomDB.repositories.InsertRowAsyncTask
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoRoomDataBase
import com.example.jetpackmvvmdemos.network.model.PostInfo
import com.example.jetpackmvvmdemos.network.repository.APIService
import com.example.jetpackmvvmdemos.network.repository.Resource
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import com.example.jetpackmvvmdemos.network.workdb.FetchResultInfo
import com.example.jetpackmvvmdemos.network.workdb.FetchRowInfpAsyncTask
import com.example.jetpackmvvmdemos.network.workdb.WorkInfoDao
import com.example.jetpackmvvmdemos.network.workdb.WorkInfoRoomDataBase
import com.example.jetpackmvvmdemos.network.workmanager.FetchWorkManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class RetroViewModel(retrofitRepository: RetrofitRepository,ctx:Context) : ViewModel() {
//,
//    CoroutineScope
    lateinit var retrofitRepository: RetrofitRepository
    var ctx:Context
    var workInfoStatus:String = ""
    var postInfoLiveData: LiveData<List<PostInfo>> = MutableLiveData()
    private var job: Job = Job()

   /* override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job*/


    init {
        this.retrofitRepository = retrofitRepository
        this.ctx = ctx
        //fetchPostInfoFromRepository()
     fetchPostInfoFromCoroutines()
       // fetchPostInfoFromCoroutineRetrofitResponse()


        }


    fun fetchPostInfoFromRepository() {
        postInfoLiveData = retrofitRepository.fetchPostInfoList()
    }


//https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
    fun fetchPostInfoFromCoroutines() {
        postInfoLiveData = liveData {
            emit(retrofitRepository.fetchPostInfoFromServer())
        }
    }


//https://android.jlelse.eu/kotlin-coroutines-and-retrofit-e0702d0b8e8f
    //https://www.reddit.com/r/androiddev/comments/c3l1qo/roomlivedataretrofitcoroutines_no_error_handling/
   fun fetchPostInfoFromCoroutineRetrofitResponse(){
       postInfoLiveData = liveData {
           emit(retrofitRepository.fetchPostInfoFromServerAsResponse())
       }
   }


}