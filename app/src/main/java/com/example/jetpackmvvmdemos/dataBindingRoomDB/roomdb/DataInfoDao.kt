package com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface DataInfoDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(resultModel:MutableList<DataInfo> )

    @Query("DELETE FROM data_info")
    fun deleteAll()

    @Query("SELECT * from data_info ORDER BY uid ASC")
    fun getAllPosts(): LiveData<MutableList<DataInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRow(resultModel:DataInfo):Long
}