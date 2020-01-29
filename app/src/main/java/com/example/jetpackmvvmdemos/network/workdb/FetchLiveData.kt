package com.example.jetpackmvvmdemos.network.workdb

import androidx.lifecycle.LiveData
import com.example.jetpackmvvmdemos.network.model.PostInfo

interface FetchLiveData {
    fun fetchLiveDataFromWorker(workLiveDataFromWorker: LiveData<List<PostInfo>>)
}