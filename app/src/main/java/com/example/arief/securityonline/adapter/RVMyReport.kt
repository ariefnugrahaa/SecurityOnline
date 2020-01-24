package com.example.arief.securityonline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arief.securityonline.R
import com.example.arief.securityonline.base.BaseListAdapter
import com.pertamina.pdsi.securityonline.Model.HomeDataModel
import kotlinx.android.synthetic.main.item_homedata.view.*

class RVMyReport (private val listener:(position:Int)-> Unit):BaseListAdapter<RecyclerView.ViewHolder, HomeDataModel.ResponseData>() {

    private var dataList = mutableListOf<HomeDataModel.ResponseData>()

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_homedata, parent, false), listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = (holder as ViewHolder).itemView.context
        holder.bindItem(dataList[position])

        when (dataList[position].status) {
            3 -> {
                (holder).mStatus.background =
                    context.resources.getDrawable(R.drawable.green_gradient)
            }
            2 -> {
                (holder).mStatus.background =
                    context.resources.getDrawable(R.drawable.yellow_gradient)
            }
            else -> {
                (holder).mStatus.background =
                    context.resources.getDrawable(R.drawable.button_gradient)
            }
        }

    }

    override fun setData(data: MutableList<HomeDataModel.ResponseData>) {
        this.dataList = data
        this.notifyDataSetChanged()

    }

    inner class ViewHolder(view: View, onClickListener: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        val mStatus: TextView = itemView.findViewById(R.id.linear)

        fun bindItem(itemModel: HomeDataModel.ResponseData) {
            itemView.tv_judul.text = itemModel.motif
            itemView.tv_peristiwa.text = itemModel.peristiwa
            itemView.tv_tanggal.text = itemModel.tanggalMasuk
            itemView.tv_nama.text = itemModel.userID
        }
    }
}