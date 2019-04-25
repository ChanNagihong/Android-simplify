package com.nagihong.androidsimplify.recyclerView

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

open class BaseBindingViewHolder<BINDING : ViewDataBinding, DATA>(
        parent: ViewGroup,
        @LayoutRes layoutId: Int,
        val binding: BINDING = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)) :
        RecyclerView.ViewHolder(binding.root) {

    var data: DATA? = null

    open fun bind() {}

    open fun bind(data: DATA) {
        this.data = data
    }

}