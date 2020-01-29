package com.example.jetpackmvvmdemos.workmanager.workermanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.jetpackmvvmdemos.network.model.PostInfo
import com.example.jetpackmvvmdemos.network.repository.RetrofitRepository
import com.example.jetpackmvvmdemos.network.workdb.*
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import com.example.jetpackmvvmdemos.workmanager.repository.WorkInfoRepository
import com.example.jetpackmvvmdemos.workmanager.roomdb.EmpInfoDao
import com.example.jetpackmvvmdemos.workmanager.roomdb.EmpInfoRoomDataBase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FetchWorkManager(context: Context, params: WorkerParameters): Worker(context,params) {

    private var  workInfoDao:EmpInfoDao
    var workInfo:LiveData<MutableList<EmpInfo>> = MutableLiveData<MutableList<EmpInfo>>()
    val retrofitRepository:RetrofitRepository
    val context:Context

    init {
        workInfoDao =  EmpInfoRoomDataBase.getDatabase(context).dataInfoDao()
        retrofitRepository = RetrofitRepository()
        this.context = context
      //  fetchInfoFromRepository()
    }

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        postDataToServer()
        val successMessageInfo = retrofitRepository.successMessage
        Log.d("FetchWorkManager","SuccessMessageInfo:::${successMessageInfo.value}")
        val data = Data.Builder()
            .put("liveData",successMessageInfo.value)
            .build()
        return Result.success(data)
    }


     fun fetchInfoFromRepository() {
         val workInfoRepository = WorkInfoRepository(workInfoDao)
         workInfo =  workInfoRepository.workInfo


     }


    fun postDataToServer(){
        fetchInfoFromRepository()
        workInfo?.let{
       val empInfoList = workInfo.value
       empInfoList?.let {
           val listSize = empInfoList.size
           if(listSize>0){
               val empInfo = EmpInfo(empInfoList[0].id,empInfoList[0].title,empInfoList[0].body,1)
               retrofitRepository.savePost(empInfo)
           }

              }
        }

    }



    /*fun insertData(liveDataInfo:WorkInfo){
        InsertRowAsyncTask(workInfoDao,this).execute(liveDataInfo)
    }*/

   /* override fun receiveResult(insertedRow: Int) {
        Log.d("FetchWorkManager","InsertedRow:::"+insertedRow);
        if(insertedRow!=-1){
            FetchRowInfpAsyncTask(workInfoDao,this).execute()
        }
    }*/

    /*override fun receiveResult(workInfo: WorkInfo?) {
        var postInfoWorkMutableLiveData: MutableLiveData<List<PostInfo>> = MutableLiveData()
        // val data = workInfo.outputData
        val jsonString = workInfo?.body
        val gson = Gson()
        var tutorialMap: Map<String, List<PostInfo>> = gson.fromJson(jsonString, object : TypeToken<Map<String, List<PostInfo>>>() {}.type)
        postInfoWorkMutableLiveData.value = tutorialMap.get("livelist")
        postInfoLiveData = postInfoWorkMutableLiveData
    }*/


}