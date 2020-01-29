package com.example.jetpackmvvmdemos.workmanager.view

import android.app.AppComponentFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackmvvmdemos.LifeCycleAwares.view.LifeCycleAwareFragment
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.replaceFragmentWithNoHistory

class WorkManagerActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        replaceFragmentWithNoHistory(WorkManagerFragment(), R.id.container_fragment)
    }
}