package com.example.jetpackmvvmdemos.dataBindingRoomDB.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.BR
import com.example.jetpackmvvmdemos.dataBindingRoomDB.roomdb.DataInfo
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.DbListItemBinding


class ListInfoAdapter(var context: Context) : RecyclerView.Adapter<ListInfoAdapter.ViewHolder>() {
    private var list: List<DataInfo> = emptyList<DataInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: DbListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.db_list_item, parent, false)
        return ListInfoAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // Log.d("ADapter", "Info:::" + list.get(position).body)ß
        holder.bind(list.get(position))
    }

    fun setAdapterList(list: List<DataInfo>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: DbListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.dataInfo, data) // BR - generated class; BR.item - 'item' is variable name declared in layout
            binding.executePendingBindings()
        }
    }
}