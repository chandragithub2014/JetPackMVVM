package com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_info")
data class DataInfo(
        @PrimaryKey(autoGenerate = true) val uid: Int,
        val body: String?,
        val title:String?) {
}