package com.harshit.covidtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class StateAdapter (val list:List<Statewise>):RecyclerView.Adapter<StateAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.activeTv.text = list[position].active
        holder.decreasedTv.text = list[position].deaths
        holder.recoverdTv.text = list[position].recovered
        holder.confirmedTv.text = list[position].confirmed
        holder.stateTv.text = list[position].state
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder(inflate: View) :RecyclerView.ViewHolder(inflate){
        val activeTv = inflate.activeTv
        val confirmedTv = inflate.confirmedTv
        val recoverdTv = inflate.recoverdTv
        val decreasedTv = inflate.DecreasedTv
        val stateTv = inflate.stateTv
    }
}



