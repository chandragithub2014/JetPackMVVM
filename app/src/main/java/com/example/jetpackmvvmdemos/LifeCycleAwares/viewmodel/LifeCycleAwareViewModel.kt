package com.example.jetpackmvvmdemos.LifeCycleAwares.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.jetpackmvvmdemos.LifeCycleAwares.model.Launcher
import kotlinx.coroutines.launch


class LifeCycleAwareViewModel:ViewModel(),LifecycleObserver {
    var launcher: LiveData<MutableList<Launcher>> = MutableLiveData<MutableList<Launcher>>()
    val mutableLiveInfo = MutableLiveData<MutableList<Launcher>>()


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun prepareLauncherInfo(){


        Log.d("LifeCycleAwareViewModel","In viewmodel onResume()....")

        var launcherList =  mutableListOf<Launcher>()
      //  val mutableLiveInfo = MutableLiveData<MutableList<Launcher>>()
        launcherList.add(Launcher("TestData1"))
        launcherList.add(Launcher("TestData2"))
        launcherList.add(Launcher("TestData3"))
        launcherList.add(Launcher("TestData4"))
        launcherList.add(Launcher("TestData5"))
        launcherList.add(Launcher("TestData6"))
        mutableLiveInfo.value = launcherList

    }
}