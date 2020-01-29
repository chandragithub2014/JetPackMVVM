package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.jetpackmvvmdemos.pagingJetPack.roomdb.DataInfo

class PageListAdapter : PagedListAdapter<DataInfo, PagingListViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: PagingListViewHolder, position: Int) {
        Log.d("Adapter","In onBindViewHolder")
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingListViewHolder =
        PagingListViewHolder(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<DataInfo>() {
            override fun areItemsTheSame(oldItem: DataInfo, newItem: DataInfo): Boolean =
                oldItem.uid == newItem.uid

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: DataInfo, newItem: DataInfo): Boolean =
                oldItem == newItem
        }
    }
}