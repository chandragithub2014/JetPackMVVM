package com.example.jetpackmvvmdemos.Launcher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackmvvmdemos.Launcher.model.Launcher

class LauncherViewModel:ViewModel() {
    var launcher:LiveData<MutableList<Launcher>>  = MutableLiveData<MutableList<Launcher>>()

    init{
        launcher = prepareLauncherInfo()
    }


    fun prepareLauncherInfo():LiveData<MutableList<Launcher>>{

        var launcherList =  mutableListOf<Launcher>()
        val mutableLiveInfo = MutableLiveData<MutableList<Launcher>>()
        launcherList.add(Launcher("Activity DataBinding"))
        launcherList.add(Launcher("Fragment DataBinding"))
        launcherList.add(Launcher("DataBinding List"))
        launcherList.add(Launcher("RoomDB List"))
        launcherList.add(Launcher("MVVM Network"))
        launcherList.add(Launcher("LifeCycleAware"))
        launcherList.add(Launcher("Pagination"))
        launcherList.add(Launcher("Navigation"))
        launcherList.add(Launcher("WorkManager"))
        mutableLiveInfo.value = launcherList
        return mutableLiveInfo

    }
}