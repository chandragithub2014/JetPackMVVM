package com.example.jetpackmvvmdemos.workmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emp_info")
data class EmpInfo(@PrimaryKey(autoGenerate = true) val id: Int,
                   val title: String?,
                   val body:String?,
                   val userId:Long) {
}
