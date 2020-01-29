package com.example.jetpackmvvmdemos.network.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackmvvmdemos.network.model.PostInfo
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import com.example.jetpackmvvmdemos.network.workdb.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FetchWorkManager(context: Context, params: WorkerParameters): Worker(context,params),FetchResult,FetchResultInfo {

    private var  workInfoDao:WorkInfoDao
    init {
        workInfoDao =  WorkInfoRoomDataBase.getDatabase(context).dataInfoDao()
        fetchInfoFromRepository()
    }

    companion object {
        var postInfoLiveData: LiveData<List<PostInfo>> = MutableLiveData()
        fun fetchLiveInfo() : LiveData<List<PostInfo>>{
            return postInfoLiveData
        }
    }
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {


        val map = mutableMapOf<String, Any?>()
        map.put("livelist",postInfoLiveData.value)

        val liveDataWork = Gson().toJson(map)


        insertData(WorkInfo(0,liveDataWork.toString()))
        val data = Data.Builder()
            .put("liveData","success")
            .build()
        return Result.success(data)
    }


    fun fetchInfoFromRepository(){
        val retrofitRepository = RetrofitRepository()
        postInfoLiveData = retrofitRepository.fetchPostInfoList()

    }

    fun insertData(liveDataInfo:WorkInfo){
        InsertRowAsyncTask(workInfoDao,this).execute(liveDataInfo)
    }

    override fun receiveResult(insertedRow: Int) {
        Log.d("FetchWorkManager","InsertedRow:::"+insertedRow);
        if(insertedRow!=-1){
            FetchRowInfpAsyncTask(workInfoDao,this).execute()
        }
    }

    override fun receiveResult(workInfo: WorkInfo?) {
        var postInfoWorkMutableLiveData: MutableLiveData<List<PostInfo>> = MutableLiveData()
        // val data = workInfo.outputData
        val jsonString = workInfo?.body
        val gson = Gson()
        var tutorialMap: Map<String, List<PostInfo>> = gson.fromJson(jsonString, object : TypeToken<Map<String, List<PostInfo>>>() {}.type)
        postInfoWorkMutableLiveData.value = tutorialMap.get("livelist")
        postInfoLiveData = postInfoWorkMutableLiveData
    }


}