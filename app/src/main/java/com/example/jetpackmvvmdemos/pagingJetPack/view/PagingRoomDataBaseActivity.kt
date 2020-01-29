package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackmvvmdemos.R

class PagingRoomDataBaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        replaceFragment()
    }

    fun replaceFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, PagingListFragment())
            .commit()
    }


    override fun onResume() {
        super.onResume()
        Log.d("PageRoomDBActivity","OnResume().....")
    }
}