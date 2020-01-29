package com.example.jetpackmvvmdemos.pagingJetPack.view


import android.content.Context
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
import com.example.jetpackmvvmdemos.pagingJetPack.viewmodel.DataInfoViewModel
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.RoomDbInfoListFragmentLayoutBinding
import kotlinx.android.synthetic.main.room_db_info_list_fragment_layout.view.*


class PagingRoomDataBaseFragment:Fragment() {

    var fragmentView: View? = null
   // private var listInfoAdapter:ListInfoAdapter?=null
    private var roomDbInfoListFragmentLayoutBinding:RoomDbInfoListFragmentLayoutBinding?=null
    val dataInfoViewModel: DataInfoViewModel by lazy { ViewModelProviders.of(this).get(DataInfoViewModel::class.java) }
    var  mContainerID:Int = -1
  //  private val dataInfoViewModel by viewModels<DataInfoViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        roomDbInfoListFragmentLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.room_db_info_list_fragment_layout, container, false)
        roomDbInfoListFragmentLayoutBinding?.lifecycleOwner = this.activity
        fragmentView = roomDbInfoListFragmentLayoutBinding?.root
        container?.let{
            mContainerID =    container.id
        }

        fragmentView?.dbrecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val adapter = ListInfoAdapter()
        fragmentView?.dbrecyclerView?.adapter = adapter


        // Subscribe the adapter to the ViewModel, so the items in the adapter are refreshed
        // when the list changes
        //  viewModel.allCheeses.observe(this, Observer(adapter::submitList))

        dataInfoViewModel.allInfo.observe(this, androidx.lifecycle.Observer{
            adapter.submitList(it)
        })

        //
            return fragmentView

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retainInstance = true
    }

    fun intRecyclerView(){
        fragmentView?.dbrecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            isNestedScrollingEnabled = true
        }
    }


    fun setAdapter(){

        val listInfoAdapter =
            ListInfoAdapter()
          //  dataInfoViewModel.allInfo.observe(this, Observer(listInfoAdapter::submitList))fragmentView?.dbrecyclerView?
        dataInfoViewModel.allInfo.observe(this, Observer {data->
            listInfoAdapter.submitList(data)

        })
      //  dataInfoViewModel.allInfo.observe(this,Observer(listInfoAdapter::submitList))
            fragmentView?.dbrecyclerView?.adapter = listInfoAdapter
             /*dataInfoViewModel.allInfo.observe(this@PagingRoomDataBaseFragment.requireActivity(), Observer { data ->
                // Update the cached copy of the words in the adapter.

                data?.let {
                    //view.textView2.text = it.toString()
                    // Log.d("RoomDBFragment","ViewModel Observer is called")
                    Log.d("Fragm","it:::"+it.toString())
                    listInfoAdapter?.setAdapterList(it)
                }
            })*/

    }













}



//https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#13