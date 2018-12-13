package com.nagihong.androidsimplify.recyclerView.expandable

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nagihong.androidsimplify.recyclerView.BaseBindingViewHolder

/**
 * Chenyikang
 * 2018 November 01
 */
abstract class SimpleRestrictedExpandableSection<Binding : ViewDataBinding, ChildBinding : ViewDataBinding, Data>(
    private val layoutId: Int,
    private val childLayoutId: Int,
    private val type: Int = layoutId,
    private val childType: Int = childLayoutId,
    var data: Data? = null
) : BaseExpandableSection() {

    var expanded = false

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            type -> BaseBindingViewHolder<Binding, Data>(parent, layoutId)
            else -> BaseBindingViewHolder<ChildBinding, Data>(parent, childLayoutId)
        }
    }

    override fun onBind(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        @Suppress("UNCHECKED_CAST")
        when (position) {
            0 -> (holder as? BaseBindingViewHolder<Binding, Data>)?.let {
                data?.run {
                    onBind(holder.binding, 0, this)
                }
            }
            else -> (holder as? BaseBindingViewHolder<ChildBinding, Data>)?.let {
                data?.run {
                    onBindChild(holder.binding, 0, position - 1, this)
                }
            }
        }
    }

    @Deprecated("this function not applied for this section", ReplaceWith("childCount()"))
    override fun childCountAt(position: Int) = 0

    override fun count() = if (null == data) 0 else 1 + if (expanded) childCount() else 0

    override fun typeAt(position: Int) = when (position) {
        0 -> type
        else -> childType
    }

    override fun collectTypes() = listOf(type, childType)

    abstract fun onBind(
        binding: Binding,
        position: Int,
        data: Data
    )

    abstract fun onBindChild(
        binding: ChildBinding,
        position: Int,
        childPosition: Int,
        data: Data
    )

    abstract fun childCount(): Int

    open fun getId(source: Data): Long = -1L

    open fun getChildId(
        source: Data,
        position: Int
    ): Long = -1L

    override fun id(position: Int): Long {
        return when (position) {
            0 -> getId(data!!)
            else -> getChildId(data!!, position - 1)
        }
    }

    open fun toggle() {
        expanded = !expanded
        notifyDataSetChanged()
    }

}