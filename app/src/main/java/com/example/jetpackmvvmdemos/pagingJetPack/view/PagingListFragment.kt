package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.pagingJetPack.viewmodel.DataInfoViewModel
import com.example.jetpackmvvmdemos.R
import kotlinx.android.synthetic.main.paginglist_layout_activity.view.*


class PagingListFragment:Fragment() {

    lateinit var fragmentView:View
    private val viewModel by viewModels<DataInfoViewModel>()

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
        val adapter = PageListAdapter()
        fragmentView.dbrecyclerView.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        viewModel.allInfo.observe(this, androidx.lifecycle.Observer{
            Log.d("PagedListFrag","In Observe.....")
            adapter.submitList(it)
        })
    }
}