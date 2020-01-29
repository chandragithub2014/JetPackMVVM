package com.example.jetpackmvvmdemos.LifeCycleAwares.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.LifeCycleAwares.model.Launcher

class LifeCycleAwareAdapter():RecyclerView.Adapter<LifeCycleAwareListViewHolder>() {
    var  launcherList:MutableList<Launcher> = mutableListOf()

    init {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifeCycleAwareListViewHolder  =
        LifeCycleAwareListViewHolder(parent)



    override fun onBindViewHolder(holder: LifeCycleAwareListViewHolder, position: Int) {
        holder.bindTo(launcherList.get(position))

    }


    override fun getItemCount(): Int = launcherList.size


    fun setist(launcherList:MutableList<Launcher>){
        this.launcherList = launcherList
        notifyDataSetChanged()
    }


}