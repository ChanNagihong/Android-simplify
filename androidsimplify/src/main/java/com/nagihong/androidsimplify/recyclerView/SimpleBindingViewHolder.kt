package com.nagihong.androidsimplify.recyclerView

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.ViewGroup

class SimpleBindingViewHolder<BINDING: ViewDataBinding, DATA>(parent: ViewGroup, @LayoutRes val layoutId: Int, private val func: (BINDING, Int, DATA) -> Unit):
        BaseBindingViewHolder<BINDING, DATA>(parent, layoutId) {

    override fun bind(data: DATA) {
        super.bind(data)
        func.invoke(binding, layoutPosition, data)
    }

}