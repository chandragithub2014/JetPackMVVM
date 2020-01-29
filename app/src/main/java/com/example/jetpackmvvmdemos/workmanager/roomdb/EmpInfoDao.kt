package com.example.jetpackmvvmdemos.workmanager.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo


@Dao
interface EmpInfoDao {

        @Query("DELETE FROM emp_info")
        fun deleteAll()

        @Query("SELECT * from emp_info ORDER BY id ASC")
        fun getAllPosts(): MutableList<EmpInfo>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertRow(resultModel: EmpInfo):Long

}