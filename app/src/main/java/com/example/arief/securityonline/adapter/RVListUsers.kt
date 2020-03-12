package com.example.arief.securityonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arief.securityonline.R
import com.example.arief.securityonline.base.BaseListAdapter
import com.example.arief.securityonline.network.model.ListRoleModel
import kotlinx.android.synthetic.main.item_list_role.view.*

class RVListUsers (private val listener:(position:Int)-> Unit):BaseListAdapter<RecyclerView.ViewHolder, ListRoleModel.ResponseData>(){

    private var dataList = mutableListOf<ListRoleModel.ResponseData>()

    override fun setData(data: MutableList<ListRoleModel.ResponseData>) {
        this.dataList = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list_role, parent, false), listener)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder)
         holder.bindItem(dataList[position])
    }

    inner class ViewHolder(view:View, onClickListener:(position: Int)->Unit):RecyclerView.ViewHolder(view){

        init {
            view.cv_list_role.setOnClickListener { onClickListener(adapterPosition) }
        }

        fun bindItem(itemModel: ListRoleModel.ResponseData){
            itemView.tv_list_role.text = itemModel.userID
        }
    }
}
