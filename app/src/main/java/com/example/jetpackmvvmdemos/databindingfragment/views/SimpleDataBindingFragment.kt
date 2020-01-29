package com.example.jetpackmvvmdemos.databindingfragment.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.jetpackmvvmdemos.databindingfragment.models.Employee
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.ActivitySimpleFragmentLayoutBinding


class SimpleDataBindingFragment:Fragment(){
    var fragmentView: View? = null

    private var fragmentBinding: ActivitySimpleFragmentLayoutBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.activity_simple_fragment_layout, container, false)
        fragmentBinding?.emp = Employee("Steve","Smith","ML","AUS")
        fragmentView = fragmentBinding?.root
        return fragmentView
    }
}