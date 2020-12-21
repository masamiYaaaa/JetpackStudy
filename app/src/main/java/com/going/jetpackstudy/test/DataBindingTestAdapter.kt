package com.going.jetpackstudy.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.going.jetpackstudy.databinding.ItemDatabindingTestBinding

class DataBindingTestAdapter : RecyclerView.Adapter<DataBindingTestAdapter.ItemViewHolder>() {
    val dataList = mutableListOf<UserData>()

    init {
        for (i in 12..22) {
            val userData = UserData("刻晴$i", i + 14, "蒙德城", 0)
            dataList.add(userData)
        }
    }

    class ItemViewHolder(private val itemBinding: ItemDatabindingTestBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: UserData) {
            itemBinding.user = item
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            ItemDatabindingTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


    override fun getItemCount() = dataList.size


}