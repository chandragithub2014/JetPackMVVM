package com.example.jetpackmvvmdemos.network.workdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "work_info")
data class WorkInfo( @PrimaryKey(autoGenerate = true) val uid: Int,
val body: String?) {
}
