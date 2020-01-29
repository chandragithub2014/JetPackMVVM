package com.example.jetpackmvvmdemos.Launcher.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.Launcher.model.Launcher

class LauncherAdapter(adapterClickListener: AdapterClickListener):RecyclerView.Adapter<LauncherListViewHolder>() {
    var  launcherList:MutableList<Launcher> = mutableListOf()
    var adapterClickListener:AdapterClickListener?=null
    init {
        this.adapterClickListener = adapterClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherListViewHolder  =
       LauncherListViewHolder(parent,adapterClickListener)



    override fun onBindViewHolder(holder: LauncherListViewHolder, position: Int) {
        holder.bindTo(launcherList.get(position))

    }


    override fun getItemCount(): Int = launcherList.size


    fun setist(launcherList:MutableList<Launcher>){
        this.launcherList = launcherList
        notifyDataSetChanged()
    }


}