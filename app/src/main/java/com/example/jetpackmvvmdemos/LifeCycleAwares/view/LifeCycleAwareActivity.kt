package com.example.jetpackmvvmdemos.LifeCycleAwares.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackmvvmdemos.Launcher.view.LauncherFragment
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.replaceFragment
import com.example.jetpackmvvmdemos.replaceFragmentWithNoHistory

class LifeCycleAwareActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        replaceFragmentWithNoHistory(LifeCycleAwareFragment(), R.id.container_fragment)
    }


}