package com.example.jetpackmvvmdemos.pagingJetPack.roomdb

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface DataInfoDao{

    @Transaction @Insert
    fun insertAll(resultModel:MutableList<DataInfo> )

    @Query("DELETE FROM data_info")
    fun deleteAll()

    @Query("SELECT * from data_info ORDER BY uid ASC")
    fun getAllPosts(): LiveData<MutableList<DataInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRow(resultModel:DataInfo):Long

    @Query("SELECT * FROM data_info")
    fun allDataByName(): DataSource.Factory<Int, DataInfo>

    @Delete
    fun delete(dataInfo: DataInfo)
}