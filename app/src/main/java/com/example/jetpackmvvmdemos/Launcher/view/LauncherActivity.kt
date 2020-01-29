package com.example.jetpackmvvmdemos.Launcher.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.replaceFragment

class LauncherActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        replaceFragment(LauncherFragment(),R.id.container_fragment)
    }
}