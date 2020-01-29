package com.example.jetpackmvvmdemos.dataBindingAdapter.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackmvvmdemos.BR
import com.example.jetpackmvvmdemos.dataBindingAdapter.models.ItemInfo
import com.example.jetpackmvvmdemos.R
import com.example.jetpackmvvmdemos.databinding.ListItemBinding


class ListInfoAdapter(var context: Context) : RecyclerView.Adapter<ListInfoAdapter.ViewHolder>() {
    private var list: List<ItemInfo> = emptyList<ItemInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item, parent, false)
        return ListInfoAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // Log.d("ADapter", "Info:::" + list.get(position).body)
        holder.bind(list.get(position))
    }

    fun setAdapterList(list: List<ItemInfo>) {
        this.list = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = list.size

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.item, data) // BR - generated class; BR.item - 'item' is variable name declared in layout
            binding.executePendingBindings()
        }
    }
}