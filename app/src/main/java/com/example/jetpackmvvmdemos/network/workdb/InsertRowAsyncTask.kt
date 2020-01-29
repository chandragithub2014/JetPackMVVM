package com.example.jetpackmvvmdemos.network.workdb

import android.os.AsyncTask


class InsertRowAsyncTask(dataInfoDao: WorkInfoDao,fetchResult: FetchResult):AsyncTask<WorkInfo,Unit,Long>() {
    private var dataInfoDao:WorkInfoDao?=null
    private var fetchResult:FetchResult?=null
    init {
        this.dataInfoDao = dataInfoDao
        this.fetchResult = fetchResult
    }
    override fun doInBackground(vararg params: WorkInfo) : Long? {

         return dataInfoDao?.insertRow(params.get(0))

    }

    override fun onPostExecute(result: Long?) {
        fetchResult?.receiveResult(result?.toInt()?:-1)
    }
}