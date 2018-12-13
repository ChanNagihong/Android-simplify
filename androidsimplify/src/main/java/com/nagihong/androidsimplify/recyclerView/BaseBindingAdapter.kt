package com.nagihong.androidsimplify.recyclerView

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Chenyikang
 * 2018 December 13
 */
abstract class BaseBindingAdapter<BINDING : ViewDataBinding, DATA> :
    RecyclerView.Adapter<SimpleBindingViewHolder<BINDING, DATA>>() {

    var data: List<DATA>? = null

    @LayoutRes
    abstract fun layoutId(): Int

    abstract fun onBind(
        binding: BINDING,
        index: Int,
        data: DATA
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SimpleBindingViewHolder<BINDING, DATA>(parent, layoutId()) { binding, index, data ->
        onBind(binding, index, data)
    }

    override fun onBindViewHolder(
        holder: SimpleBindingViewHolder<BINDING, DATA>,
        position: Int
    ) = holder.bind(data!![position])

    override fun getItemCount() = data?.size ?: 0

}