package com.example.jetpackmvvmdemos.LifeCycleAwares.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.LifeCycleAwares.viewmodel.LifeCycleAwareViewModel
import com.example.jetpackmvvmdemos.R
import kotlinx.android.synthetic.main.paginglist_layout_activity.view.*

class LifeCycleAwareFragment: Fragment(), LifecycleOwner {

    lateinit var fragmentView:View
    private val viewModel by activityViewModels<LifeCycleAwareViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.getLifecycle()?.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView =  inflater.inflate(R.layout.paginglist_layout_activity,container,false)
        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycleAwareFragment","In onResume()")
        processAndDisplayData()
    }


    fun processAndDisplayData(){
        fragmentView.dbrecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val adapter = LifeCycleAwareAdapter()
        fragmentView.dbrecyclerView.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        viewModel.mutableLiveInfo.observe(this, androidx.lifecycle.Observer{
            Log.d("PagedListFrag","In Observe.....")
            adapter.setist(it)
        })
    }
}