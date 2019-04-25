package com.nagihong.androidsimplify.recyclerView

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseViewHolder<DATA>(parent: ViewGroup, @LayoutRes layoutId: Int) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

  var data: DATA? = null

  open fun bind() {}

  open fun bind(data: DATA) {
    this.data = data
  }

}