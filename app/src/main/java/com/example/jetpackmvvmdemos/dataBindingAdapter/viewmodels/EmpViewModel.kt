package com.example.jetpackmvvmdemos.dataBindingAdapter.viewmodels

import androidx.lifecycle.ViewModel
import com.example.jetpackmvvmdemos.dataBindingAdapter.models.ItemInfo

class EmpViewModel:ViewModel() {
    var empInfoList:MutableList<ItemInfo>
    init {
        empInfoList = mutableListOf<ItemInfo>()
        empInfoList.add(ItemInfo("https://via.placeholder.com/50x50.png?text=1","ABC","DEF"))
        empInfoList.add(ItemInfo("https://via.placeholder.com/50x50.png?text=2","GHI","JKL"))
        empInfoList.add(ItemInfo("https://via.placeholder.com/50x50.png?text=3","MNO","PQR"))
        empInfoList.add(ItemInfo("https://via.placeholder.com/50x50.png?text=4","STU","VWX"))


    }

}