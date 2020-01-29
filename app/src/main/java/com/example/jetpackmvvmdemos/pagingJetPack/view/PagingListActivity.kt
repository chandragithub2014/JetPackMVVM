package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.pagingJetPack.viewmodel.DataInfoViewModel
import com.example.jetpackmvvmdemos.R
import kotlinx.android.synthetic.main.paginglist_layout_activity.*


class PagingListActivity:AppCompatActivity() {
    private val viewModel by viewModels<DataInfoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paginglist_layout_activity)
      //  val roomDbInfoListFragmentLayoutBinding: RoomDbInfoListFragmentLayoutBinding = DataBindingUtil.setContentView(this, R.layout.room_db_info_list_fragment_layout)

        dbrecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val adapter = PageListAdapter()
        dbrecyclerView.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        viewModel.allInfo.observe(this, androidx.lifecycle.Observer{
            adapter.submitList(it)
        })

    }
}