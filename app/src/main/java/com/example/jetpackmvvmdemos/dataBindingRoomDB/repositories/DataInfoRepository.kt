package com.example.jetpackmvvmdemos.dataBindingRoomDB.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoDao

class DataInfoRepository(private val dataInfoDao: DataInfoDao):FetchResult{

    val allInfo: LiveData<MutableList<DataInfo>> = dataInfoDao.getAllPosts()
    val insertedId:MutableLiveData<Int> = MutableLiveData<Int>()
    init {
        insertedId.value = -1
    }

    fun insertAllData(info:MutableList<DataInfo>){
        InsertAsyncTask(dataInfoDao).execute(info)
    }

    fun insertRowInfo(singleRow:DataInfo){
        InsertRowAsyncTask(dataInfoDao,this).execute(singleRow)
    }

    override fun receiveResult(insertedRow: Int) {
        insertedId.value = insertedRow
        insertedRowId()
    }

    fun insertedRowId():LiveData<Int>{
        return insertedId
    }
}