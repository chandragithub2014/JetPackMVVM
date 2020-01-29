package com.example.jetpackmvvmdemos.Launcher.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.dataBindingActivity.views.SimpleDataBindingActivity
import com.example.jetpackmvvmdemos.dataBindingAdapter.views.DataBindingListActivity
import com.example.jetpackmvvmdemos.databindingfragment.views.SimpleDataBindingFragmentActivity
import com.example.jetpackmvvmdemos.dataBindingRoomDB.views.RoomDataBaseActivity
import com.example.jetpackmvvmdemos.Launcher.viewmodel.LauncherViewModel
import com.example.jetpackmvvmdemos.LifeCycleAwares.view.LifeCycleAwareActivity
import com.example.jetpackmvvmdemos.pagingJetPack.view.PagingRoomDataBaseActivity
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.navigationJetPack.views.NavigationMainActivity
import com.example.jetpackmvvmdemos.network.view.NetworkActivity
import com.example.jetpackmvvmdemos.openActivity
import com.example.jetpackmvvmdemos.workmanager.view.WorkManagerActivity
import com.example.jetpackmvvmdemos.workmanagersample.views.WorkMangerSampleActivity
import kotlinx.android.synthetic.main.paginglist_layout_activity.view.*

class LauncherFragment:Fragment(),AdapterClickListener {

    lateinit var fragmentView:View
    private val viewModel by viewModels<LauncherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView =  inflater.inflate(R.layout.paginglist_layout_activity,container,false)
        processAndDisplayData()
        return fragmentView
    }


    fun processAndDisplayData(){
        fragmentView.dbrecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val adapter = LauncherAdapter(this)
        fragmentView.dbrecyclerView.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        viewModel.launcher.observe(this, androidx.lifecycle.Observer{
            Log.d("PagedListFrag","In Observe.....")
            adapter.setist(it)
        })
    }

    override fun adapterClicked(position: Int, clickedItem: String?) {
        Log.d("LauncherFragment","Adapter Position $position and clicked Item is $clickedItem")
        launchJetPackSample(position, clickedItem)
    }

    fun launchJetPackSample(position:Int,clickedItem: String?){
        when(clickedItem){
            "Activity DataBinding"-> context?.openActivity(SimpleDataBindingActivity::class.java)
            "Fragment DataBinding"-> context?.openActivity(SimpleDataBindingFragmentActivity::class.java)
            "DataBinding List"-> context?.openActivity(DataBindingListActivity::class.java)
            "MVVM Network"-> context?.openActivity(NetworkActivity::class.java)
            "Pagination"-> context?.openActivity(PagingRoomDataBaseActivity::class.java)
            "RoomDB List"->context?.openActivity(RoomDataBaseActivity::class.java)
            "LifeCycleAware"->context?.openActivity(LifeCycleAwareActivity::class.java)
            "Navigation"->context?.openActivity(NavigationMainActivity::class.java)
            "WorkManager"->context?.openActivity(WorkMangerSampleActivity::class.java)

        }
    }
}