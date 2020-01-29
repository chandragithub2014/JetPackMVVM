package com.example.jetpackmvvmdemos.pagingJetPack.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.example.jetpackmvvmdemos.pagingJetPack.roomdb.DataInfoRoomDataBase


class DataInfoViewModel(application: Application) : AndroidViewModel(application) {

   val  dataInfoDao = DataInfoRoomDataBase.getDatabase(application).dataInfoDao()
    val allInfo = dataInfoDao.allDataByName().toLiveData(Config(pageSize = 10,
        enablePlaceholders = true,
        maxSize = 200))


   /* var allInfo: LiveData<PagedList<DataInfo>> = MutableLiveData()
    lateinit var dataInfoDao:DataInfoDao

     init{
         dataInfoDao = DataInfoRoomDataBase.getDatabase(application).dataInfoDao()
             allInfo = dataInfoDao.allDataByName().toLiveData(Config(pageSize = 10,
                 enablePlaceholders = true
                     ,maxSize = 200))
     }
*/

    /*fun remove(dataInfo: DataInfo) = ioThread {
        dataInfoDao.delete(dataInfo)
    }
*/

   /* fun getAllRows():LiveData<PagedList<DataInfo>>{
        allInfo = dataInfoDao.allDataByName().toLiveData(Config(pageSize = 30,
            enablePlaceholders = true
            ,maxSize = 200))
        Log.d("ViewModel", "All Indo:::"+allInfo.value)
        return  allInfo

    }*/

}