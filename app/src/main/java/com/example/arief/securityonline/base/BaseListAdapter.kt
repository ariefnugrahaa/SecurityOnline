package com.example.arief.securityonline.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<H, T>: RecyclerView.Adapter<H>() where H : RecyclerView.ViewHolder {
    abstract fun setData(data: MutableList<T>)
}