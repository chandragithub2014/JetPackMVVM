package com.example.jetpackmvvmdemos.dataBindingRoomDB.repositories

import android.os.AsyncTask
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoDao

class InsertAsyncTask(dataInfoDao: DataInfoDao):AsyncTask<MutableList<DataInfo>,Unit,Unit>() {
    private var dataInfoDao:DataInfoDao?=null
    init {
        this.dataInfoDao = dataInfoDao
    }
    override fun doInBackground(vararg params: MutableList<DataInfo>) {

         dataInfoDao?.insertAll(params.get(0))

    }
}