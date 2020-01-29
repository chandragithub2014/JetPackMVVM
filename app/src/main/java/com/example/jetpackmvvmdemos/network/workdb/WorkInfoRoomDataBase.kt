package com.example.jetpackmvvmdemos.network.workdb

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase




@Database(entities = arrayOf(WorkInfo::class), version = 2)
abstract class WorkInfoRoomDataBase : RoomDatabase() {

    abstract fun dataInfoDao(): WorkInfoDao

    companion object {
        private var INSTANCE: WorkInfoRoomDataBase? = null

        fun getDatabase(context: Context): WorkInfoRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkInfoRoomDataBase::class.java,
                    "workinfo_database"
                )   .fallbackToDestructiveMigration()
                    .addCallback(SRoomDatabaseCallback())
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }


   private class SRoomDatabaseCallback : RoomDatabase.Callback() {
       override fun onCreate(db: SupportSQLiteDatabase) {
           super.onCreate(db)
           PopulateDbAsyncTask(INSTANCE)
               .execute()
       }


    }

    class PopulateDbAsyncTask(db: WorkInfoRoomDataBase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.dataInfoDao()
        override fun doInBackground(vararg p0: Unit?) {
            Log.d("DataInfoRoomDB","Delete.....")
            noteDao?.deleteAll()

        }
    }



}