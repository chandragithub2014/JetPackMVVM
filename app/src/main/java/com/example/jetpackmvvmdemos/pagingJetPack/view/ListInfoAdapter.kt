package com.example.jetpackmvvmdemos.pagingJetPack.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.BR
import com.example.jetpackmvvmdemos.pagingJetPack.roomdb.DataInfo

import com.example.jetpackmvvmdemos.R


import com.example.jetpackmvvmdemos.databinding.PageDbListItemBinding


class ListInfoAdapter : PagedListAdapter<DataInfo, ListInfoAdapter.ViewHolder>(diffCallback) {
    private var list: List<DataInfo> = emptyList<DataInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: PageDbListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.page_db_list_item, parent, false)
        return ListInfoAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     //  Log.d("ADapter", "Info:::$position" + list.get(position))
        holder.bind(/*list.get(position)*/getItem(position))
    }

    fun setAdapterList(list: PagedList<DataInfo>) {
        this.list = list
        notifyDataSetChanged()
    }
    fun refreshList(){
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: PageDbListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var data : DataInfo? = null
        fun bind(data: DataInfo?) {
            this.data = data
            binding.setVariable(BR.dataInfo, data) // BR - generated class; BR.item - 'item' is variable name declared in layout
            binding.executePendingBindings()
        }
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see android.support.v7.util.DiffUtil
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