package com.example.jetpackmvvmdemos.dataBindingActivity.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.jetpackmvvmdemos.dataBindingActivity.models.Employee
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.ActivitySimpleLayoutBinding

class SimpleDataBindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var simpleBinding: ActivitySimpleLayoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_simple_layout)
        simpleBinding.emp = Employee("John","Williams","NY","US")
    }

}