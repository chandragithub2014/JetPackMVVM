package com.example.jetpackmvvmdemos.dataBindingRoomDB.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackmvvmdemos.dataBindingRoomDB.repositories.DataInfoRepository
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoRoomDataBase

class DataInfoViewModel(application: Application) : AndroidViewModel(application) {

    val allInfo: LiveData<MutableList<DataInfo>>
    var dummyData: MutableList<DataInfo>
    var insertedRow:LiveData<Int> = MutableLiveData<Int>()
    private val repository: DataInfoRepository

    init{
        dummyData = mutableListOf<DataInfo>()
        prepareDummyData()
        val dataInfoDao = DataInfoRoomDataBase.getDatabase(application).dataInfoDao()
        repository = DataInfoRepository(dataInfoDao)
     //   repository.insertAllData(dummyData)
        allInfo = repository.allInfo

    }


    fun prepareDummyData(){
        dummyData.add(DataInfo(0,"ABC","ABCTitle"))
        dummyData.add(DataInfo(0,"DEF","DEFTitle"))
        dummyData.add(DataInfo(0,"GHI","GHITitle"))
        dummyData.add(DataInfo(0,"JKL","JKLTitle"))
    }

    fun insertData(rowInfo:DataInfo){
         repository.insertRowInfo(rowInfo)
         insertedRow = repository.insertedId
    }




}