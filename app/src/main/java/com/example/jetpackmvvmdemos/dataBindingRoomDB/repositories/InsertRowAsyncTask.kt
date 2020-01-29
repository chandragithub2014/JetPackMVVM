package com.example.jetpackmvvmdemos.dataBindingRoomDB.repositories

import android.os.AsyncTask
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfoDao

class InsertRowAsyncTask(dataInfoDao: DataInfoDao,fetchResult: FetchResult):AsyncTask<DataInfo,Unit,Long>() {
    private var dataInfoDao:DataInfoDao?=null
    private var fetchResult:FetchResult?=null
    init {
        this.dataInfoDao = dataInfoDao
        this.fetchResult = fetchResult
    }
    override fun doInBackground(vararg params: DataInfo) : Long? {

         return dataInfoDao?.insertRow(params.get(0))

    }

    override fun onPostExecute(result: Long?) {
        fetchResult?.receiveResult(result?.toInt()?:-1)
    }
}