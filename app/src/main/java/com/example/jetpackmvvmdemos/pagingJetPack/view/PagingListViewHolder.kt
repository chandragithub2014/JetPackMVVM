package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.pagingJetPack.roomdb.DataInfo
import com.example.jetpackmvvmdemos.R

class PagingListViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var cheese : DataInfo? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(cheese : DataInfo?) {
        Log.d("Adapter","Rebind.....")
        this.cheese = cheese
        nameView.text = cheese?.title
    }
}