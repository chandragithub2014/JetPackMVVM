package com.example.jetpackmvvmdemos.network.workdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WorkInfoDao {

        @Query("DELETE FROM work_info")
        fun deleteAll()

        @Query("SELECT * from work_info ORDER BY uid DESC")
        fun getAllPosts(): WorkInfo

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertRow(resultModel: WorkInfo):Long

}