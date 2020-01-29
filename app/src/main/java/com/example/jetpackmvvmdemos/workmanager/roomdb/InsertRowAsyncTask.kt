package com.example.jetpackmvvmdemos.workmanager.roomdb

import android.os.AsyncTask
import com.example.jetpackmvvmdemos.network.workdb.FetchInsertResult
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo


class InsertRowAsyncTask(dataInfoDao: EmpInfoDao,fetchResult: FetchInsertResult):AsyncTask<EmpInfo,Unit,Long>() {
    private var dataInfoDao:EmpInfoDao?=null
    private var fetchResult: FetchInsertResult?=null
    init {
        this.dataInfoDao = dataInfoDao
        this.fetchResult = fetchResult
    }
    override fun doInBackground(vararg params: EmpInfo) : Long? {
         return dataInfoDao?.insertRow(params.get(0))
    }

    override fun onPostExecute(result: Long?) {
        fetchResult?.receiveResult(result?.toInt()?:-1)
    }
}