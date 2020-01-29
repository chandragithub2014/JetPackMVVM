package com.example.jetpackmvvmdemos.workmanager.roomdb

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.jetpackmvvmdemos.workmanager.model.EmpInfo


@Database(entities = arrayOf(EmpInfo::class), version = 2)
abstract class EmpInfoRoomDataBase : RoomDatabase() {

    abstract fun dataInfoDao(): EmpInfoDao

    companion object {
        private var INSTANCE: EmpInfoRoomDataBase? = null

        fun getDatabase(context: Context): EmpInfoRoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmpInfoRoomDataBase::class.java,
                    "empinfo_database"
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

    class PopulateDbAsyncTask(db: EmpInfoRoomDataBase?) : AsyncTask<Unit, Unit, Unit>() {
        private val noteDao = db?.dataInfoDao()
        override fun doInBackground(vararg p0: Unit?) {
            Log.d("DataInfoRoomDB","Delete.....")
            noteDao?.deleteAll()

        }
    }



}