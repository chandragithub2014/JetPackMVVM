package com.example.jetpackmvvmdemos.workmanager.roomdb
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo

interface FetchResultsInfo {
    fun receiveResult(workInfo: MutableList<EmpInfo>)
}