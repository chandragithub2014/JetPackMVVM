package com.example.jetpackmvvmdemos.workmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo
import com.example.jetpackmvvmdemos.workmanager.repository.WorkInfoRepository
import com.example.jetpackmvvmdemos.workmanager.roomdb.EmpInfoRoomDataBase

class WorkInfoViewModel(application: Application): AndroidViewModel(application) {

    var liveInsertedData:LiveData<Int> = MutableLiveData<Int>()
    var allInfo:LiveData<MutableList<EmpInfo>> = MutableLiveData<MutableList<EmpInfo>>()
    private val repository: WorkInfoRepository

    init{
        val dataInfoDao = EmpInfoRoomDataBase.getDatabase(application).dataInfoDao()
        repository = WorkInfoRepository(dataInfoDao)
        allInfo = repository.workInfo
    }


    fun insertData(rowInfo: EmpInfo){
        repository.insertRowInfo(rowInfo)
        liveInsertedData = repository.insertedId
    }

    fun fetchDataFromRepository(){
        repository.fetchAllData()
        allInfo = repository.workInfo

    }
}
