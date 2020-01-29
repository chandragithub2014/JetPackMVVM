package com.example.jetpackmvvmdemos.network.workdb

import android.os.AsyncTask


class FetchRowInfpAsyncTask(dataInfoDao: WorkInfoDao?, fetchResult: FetchResultInfo):AsyncTask<Unit,Unit,WorkInfo>() {
    private var dataInfoDao:WorkInfoDao?=null
    private var fetchResult:FetchResultInfo?=null
    init {
        this.dataInfoDao = dataInfoDao
        this.fetchResult = fetchResult
    }
    override fun doInBackground(vararg params: Unit) : WorkInfo? {

         return dataInfoDao?.getAllPosts()

    }

    override fun onPostExecute(result: WorkInfo?) {
        fetchResult?.receiveResult(result)
    }
}