package com.nagihong.androidsimplify.recyclerView.expandable

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nagihong.androidsimplify.recyclerView.BaseBindingViewHolder

class Section<BINDING : ViewDataBinding, DATA>(
    private val layoutId: Int,
    private val onBind: ((BINDING, Int, DATA) -> Unit),
    var data: List<DATA>? = null,
    private val type: Int = layoutId
) : BaseSection() {

    var count = NO_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseBindingViewHolder<BINDING, DATA>(parent, layoutId)

    override fun onBind(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        @Suppress("UNCHECKED_CAST")
        val simpleHolder = holder as BaseBindingViewHolder<BINDING, DATA>
        data?.get(position)
            ?.run { onBind.invoke(simpleHolder.binding, position, this) }
    }

    override fun count() = data?.size ?: 0

    override fun type() = type
}