package com.example.arief.securityonline.adapter

import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arief.securityonline.base.BaseListAdapter
import com.pertamina.pdsi.securityonline.Model.HomeDataModel

class RVItem (private val position:(position:Int)-> Unit):BaseListAdapter<RecyclerView.ViewHolder, HomeDataModel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setData(data: MutableList<HomeDataModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class ViewHolder(view:View, onClickListener: DialogInterface.OnClickListener)