package com.myuidemo.ui.adaptor

import android.app.Activity
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import com.myuidemo.BR
import com.myuidemo.R
import com.myuidemo.api.response.PlayerModel
import com.myuidemo.databinding.ItemPlayerBinding
import com.myuidemo.ui.activity.MainActivity


class TeamRecyclerViewAdaptor(
    list: ArrayList<PlayerModel>?,
    activity: Activity
): RecyclerView.Adapter<TeamRecyclerViewAdaptor.MyViewHolder>() {
    var list: ArrayList<com.myuidemo.api.response.PlayerModel>? = list
    var context:Activity=activity

    class MyViewHolder(binding: ItemPlayerBinding) :RecyclerView.ViewHolder(binding.root) {
        var binding: ItemPlayerBinding
            internal set

        init {
            this.binding = binding
            this.binding.executePendingBindings()
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {

       return MyViewHolder(DataBindingUtil.inflate<ItemPlayerBinding>(LayoutInflater.from(parent.context),R.layout.item_player,parent,false))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val displayMetrics = DisplayMetrics()
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        if(list!!.size==1){
            holder.binding.rootContent.layoutParams.width = width
        }else if(list!!.size==2){
            holder.binding.rootContent.layoutParams.width = (width*0.50).toInt()
        }else if(list!!.size==3){
            holder.binding.rootContent.layoutParams.width = (width*0.33).toInt()
        }else if(list!!.size==4){
            holder.binding.rootContent.layoutParams.width = (width*0.25).toInt()
        }
        holder.binding.setVariable(BR.item,list!!.get(position))

    }



}