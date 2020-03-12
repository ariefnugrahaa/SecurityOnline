package com.example.arief.securityonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.arief.securityonline.R
import com.example.arief.securityonline.base.BaseListAdapter
import com.example.arief.securityonline.network.model.ListFollowUp
import kotlinx.android.synthetic.main.item_list_followup.view.*

class RVListFollowUp(private val listener:(position:Int) -> Unit):BaseListAdapter<RecyclerView.ViewHolder, ListFollowUp.ResponseData>() {

    private var dataList = mutableListOf<ListFollowUp.ResponseData>()

    override fun setData(data: MutableList<ListFollowUp.ResponseData>) {
        this.dataList = data
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FollowupViewHolder(layoutInflater.inflate(R.layout.item_list_followup, parent, false), listener )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is FollowupViewHolder -> holder.bindItem(dataList[position])
        }
    }

    inner class FollowupViewHolder(view:View, onClickListener:(position:Int) -> Unit): RecyclerView.ViewHolder(view){

        fun bindItem(itemModel: ListFollowUp.ResponseData){
            itemView.tv_followup_kerugian.text = itemModel.kerugian
            itemView.tv_followup_tindakan.text = itemModel.tindakan
            itemView.tv_followup_createdby.text = itemModel.createdby
            itemView.tv_followup_date.text = itemModel.date
        }
    }
}

