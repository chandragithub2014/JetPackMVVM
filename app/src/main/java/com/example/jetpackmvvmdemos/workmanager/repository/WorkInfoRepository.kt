package com.example.jetpackmvvmdemos.workmanager.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoDao
import com.example.jetpackmvvmdemos.network.workdb.FetchInsertResult
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import com.example.jetpackmvvmdemos.workmanager.roomdb.EmpInfoDao
import com.example.jetpackmvvmdemos.workmanager.roomdb.FetchResultsInfo
import com.example.jetpackmvvmdemos.workmanager.roomdb.FetchRowsAsyncTask

import com.example.jetpackmvvmdemos.workmanager.roomdb.InsertRowAsyncTask

class WorkInfoRepository(private val dataInfoDao: EmpInfoDao):FetchInsertResult,FetchResultsInfo{

  //  val allInfo: LiveData<MutableList<DataInfo>> = dataInfoDao.getAllPosts()

    val insertedId:MutableLiveData<Int> = MutableLiveData<Int>()
    var workInfo:MutableLiveData<MutableList<EmpInfo>> = MutableLiveData<MutableList<EmpInfo>>()


    init {
       // insertedId.postValue = -1
        insertedId.postValue(-1)
       // workInfo.value = dataInfoDao.getAllPosts()
        FetchRowsAsyncTask(dataInfoDao,this).execute()
    }

    fun insertRowInfo(singleRow:EmpInfo){
        InsertRowAsyncTask(dataInfoDao,this).execute(singleRow)
    }

    override fun receiveResult(insertedRow: Int) {
        insertedId.value = insertedRow
        insertedRowId()
    }

    fun insertedRowId():LiveData<Int>{
        return insertedId
    }

    override fun receiveResult(workInfoList: MutableList<EmpInfo>) {
        workInfo.value = workInfoList
    }

    fun fetchAllData(){
         workInfo.value = dataInfoDao.getAllPosts()
    }
}