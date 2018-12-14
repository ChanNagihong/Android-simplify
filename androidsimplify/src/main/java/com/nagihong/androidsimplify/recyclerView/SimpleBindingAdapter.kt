package com.nagihong.androidsimplify.recyclerView

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes

open class SimpleBindingAdapter<BINDING : ViewDataBinding, DATA>(@LayoutRes private val layoutId: Int, private val func: (BINDING, Int, DATA) -> Unit) :
    BaseBindingAdapter<BINDING, DATA>() {

    var onGetItemId: ((position: Int, data: DATA) -> Long)? = null

    override fun layoutId() = layoutId

    override fun onBind(
        binding: BINDING,
        index: Int,
        data: DATA
    ) {
        binding.root.setOnClickListener { onItemClick?.invoke(binding, index, data) }
        func.invoke(binding, index, data)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun getItemId(position: Int): Long {
        return onGetItemId?.invoke(position, data!![position]) ?: super.getItemId(position)
    }

}