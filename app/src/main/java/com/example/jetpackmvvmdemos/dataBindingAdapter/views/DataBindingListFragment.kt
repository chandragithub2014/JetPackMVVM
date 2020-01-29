package com.example.jetpackmvvmdemos.dataBindingAdapter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackmvvmdemos.dataBindingAdapter.viewmodels.EmpViewModel
import kotlinx.android.synthetic.main.emp_info_list_fragment_layout.view.*

import com.example.jetpackmvvmdemos.R

class DataBindingListFragment: Fragment() {

    var fragmentView: View? = null
    private var listAdapter: ListInfoAdapter? = null
    private var listLayoutBinding: com.example.jetpackmvvmdemos.databinding.EmpInfoListFragmentLayoutBinding? = null
   // lateinit var empViewModel: EmpViewModel
    val empViewModel: EmpViewModel by lazy { ViewModelProviders.of(this).get(EmpViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.emp_info_list_fragment_layout, container, false)
        fragmentView = listLayoutBinding?.root
        initAdapter()
        setAdapter()
        fetchEmpInfo()
        return fragmentView
    }




    fun fetchEmpInfo() {
        listAdapter?.setAdapterList(empViewModel.empInfoList)

    }


    fun setAdapter(){
        fragmentView?.emprecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = listAdapter
        }
    }

    private fun initAdapter() {
        listAdapter = ListInfoAdapter(this@DataBindingListFragment.requireActivity())
    }
}