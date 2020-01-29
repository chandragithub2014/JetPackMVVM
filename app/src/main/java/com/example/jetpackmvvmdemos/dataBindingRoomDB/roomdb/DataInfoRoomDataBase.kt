package com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase




@Database(entities = arrayOf(DataInfo::class), version = 2)
abstract class DataInfoRoomDataBase : RoomDatabase() {

    abstract fun dataInfoDao(): DataInfoDao

    companion object {
        private var INSTANCE: DataInfoRoomDataBase? = null

        fun getDatabase(context: Context): DataInfoRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataInfoRoomDataBase::class.java,
                    "datainfo_database"
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

    class PopulateDbAsyncTask(db: DataInfoRoomDataBase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.dataInfoDao()
        override fun doInBackground(vararg p0: Unit?) {
            Log.d("DataInfoRoomDB","Delete.....")
            noteDao?.deleteAll()

        }
    }



}