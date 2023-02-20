package com.example.bycalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bycalculator.databinding.ItemHistoryBinding


class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    private var list = emptyList<String>()

    class MyViewHolder(val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.itemView.findViewById<TextView>(R.id.txtLog).text = myList[position]
        holder.binding.tvHistory.text = list[position]

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(newList: List<String>) {
        list = newList
        notifyDataSetChanged()
    }


}