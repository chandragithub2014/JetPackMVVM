package com.example.jetpackmvvmdemos.LifeCycleAwares.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.LifeCycleAwares.model.Launcher
import com.example.jetpackmvvmdemos.R

class LifeCycleAwareListViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.launcher_list_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var launcher : Launcher? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(launcher: Launcher?) {
        Log.d("Adapter","Rebind.....")
        this.launcher = launcher
        nameView.text = launcher?.itemName

    }
}