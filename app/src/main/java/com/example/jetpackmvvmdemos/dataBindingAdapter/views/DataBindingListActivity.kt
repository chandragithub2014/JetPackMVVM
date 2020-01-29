package com.example.jetpackmvvmdemos.dataBindingAdapter.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackmvvmdemos.R

class DataBindingListActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        replaceFragment()
    }

    fun replaceFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, DataBindingListFragment())
            .commit()
    }
}