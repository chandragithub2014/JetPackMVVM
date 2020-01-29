package com.example.jetpackmvvmdemos.pagingJetPack.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_info")
data class DataInfo(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        val body: String?,
        val title:String?) {
}