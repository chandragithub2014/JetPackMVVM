package com.example.jetpackmvvmdemos.workmanager.roomdb

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo



class FetchRowsAsyncTask(dataInfoDao: EmpInfoDao?, fetchResult: FetchResultsInfo):AsyncTask<Unit,Unit, MutableList<EmpInfo>>() {
    private var dataInfoDao:EmpInfoDao?=null
    private var fetchResult:FetchResultsInfo?=null
    init {
        this.dataInfoDao = dataInfoDao
        this.fetchResult = fetchResult
    }
    override fun doInBackground(vararg params: Unit) : MutableList<EmpInfo>? {
        val allPosts = dataInfoDao?.getAllPosts()
        Log.d("FetchRows","RowsInfo${allPosts}")
         return allPosts

    }

    override fun onPostExecute(result: MutableList<EmpInfo>) {
    //    Log.d("FetchRows","RowsInfo${result.value}")
        fetchResult?.receiveResult(result)
    }
}
