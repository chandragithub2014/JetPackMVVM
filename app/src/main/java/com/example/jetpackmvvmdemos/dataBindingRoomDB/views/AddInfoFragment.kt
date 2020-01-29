package com.example.jetpackmvvmdemos.dataBindingRoomDB.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.dataBindingRoomDB.viewmodels.DataInfoViewModel
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.hideKeyboard
import kotlinx.android.synthetic.main.add_info_fragment_layout.view.*

class AddInfoFragment: Fragment() {

    var fragmentView: View? = null
    val dataInfoViewModel: DataInfoViewModel by lazy { ViewModelProviders.of(this).get(DataInfoViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView =  inflater.inflate(R.layout.add_info_fragment_layout,container,false)
        fragmentView?.add_info?.setOnClickListener {
            fragmentView?.add_info?.hideKeyboard()
            insertInfo()
        }
        return  fragmentView;
    }




   fun insertInfo(){
       val name = fragmentView?.body_input?.text
       val inputTitle = fragmentView?.title_input?.text
       Log.d("AddInfoFragment","Name:::$name title:::$inputTitle")
       dataInfoViewModel.insertData(DataInfo(0,name.toString(),inputTitle.toString()))

       dataInfoViewModel.insertedRow.observe(this, Observer {
           if(it!=-1){
               Toast.makeText(context,"Inserted Succesfully"+it,Toast.LENGTH_LONG).show()
               activity?.apply{
                   supportFragmentManager.popBackStackImmediate()
               }
           }
       })


   }



}