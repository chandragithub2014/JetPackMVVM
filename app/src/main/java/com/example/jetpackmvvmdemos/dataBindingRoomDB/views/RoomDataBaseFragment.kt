package com.example.jetpackmvvmdemos.dataBindingRoomDB.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.dataBindingRoomDB.viewmodels.DataInfoViewModel
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.RoomDbInfoListFragmentLayoutBinding
import kotlinx.android.synthetic.main.room_db_info_list_fragment_layout.view.*


class RoomDataBaseFragment:Fragment() {

    var fragmentView: View? = null
    private var listInfoAdapter:ListInfoAdapter?=null
    private var roomDbInfoListFragmentLayoutBinding:RoomDbInfoListFragmentLayoutBinding?=null
    val dataInfoViewModel: DataInfoViewModel by lazy { ViewModelProviders.of(this).get(DataInfoViewModel::class.java) }
    var  mContainerID:Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        roomDbInfoListFragmentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.room_db_info_list_fragment_layout, container, false)
        roomDbInfoListFragmentLayoutBinding?.lifecycleOwner = this
        fragmentView = roomDbInfoListFragmentLayoutBinding?.root
        container?.let{
            mContainerID =    container.id
        }


        fragmentView?.floatingActionButton?.setOnClickListener {
            launchAddFragment()
        }

            initAdapter()
            setAdapter()
            return fragmentView

    }



    fun setAdapter(){
        fragmentView?.dbrecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = listInfoAdapter
        }
        dataInfoViewModel.allInfo.observe(this, Observer { data ->
            // Update the cached copy of the words in the adapter.

            data?.let {
                //view.textView2.text = it.toString()
               // Log.d("RoomDBFragment","ViewModel Observer is called")
                listInfoAdapter?.setAdapterList(it)
            }
        })
    }


    private fun initAdapter() {
        listInfoAdapter =
            ListInfoAdapter(this@RoomDataBaseFragment.requireActivity())
    }


     fun launchAddFragment(){
         activity?.apply {
                  supportFragmentManager
                 .beginTransaction()
                 .replace(mContainerID, AddInfoFragment())
                      .addToBackStack("")
                 .commit()
         }
     }


}



//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#13