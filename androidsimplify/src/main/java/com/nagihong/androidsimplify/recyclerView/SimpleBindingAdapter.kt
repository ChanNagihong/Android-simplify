package com.nagihong.androidsimplify.recyclerView

import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.View

open class SimpleBindingAdapter<BINDING : ViewDataBinding, DATA>(@LayoutRes private val layoutId: Int, private val func: (BINDING, Int, DATA) -> Unit) :
    SimpleAbstractBindingAdapter<BINDING, DATA>() {

    constructor(
        layoutId: Int,
        func: (BINDING, Int, DATA) -> Unit,
        getItemId: ((Int, DATA) -> Long)? = null
    ) : this(layoutId, func) {
        onGetItemId = getItemId
    }

    var onGetItemId: ((position: Int, data: DATA) -> Long)? = null
    var onItemClick: ((binding: BINDING, position: Int, data: DATA) -> Unit)? = null

    override fun layoutId() = layoutId

    override fun onBind(
        binding: BINDING,
        index: Int,
        data: DATA
    ) {
        binding.root.setOnClickListener(object : View.OnClickListener {

            var lastClickTime = 0L

            override fun onClick(v: View?) {
                val current = System.currentTimeMillis()
                if (current - lastClickTime > 500) {
                    onItemClick?.invoke(binding, index, data)
                    lastClickTime = current
                }
            }

        })
        func.invoke(binding, index, data)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun getItemId(position: Int): Long {
        return onGetItemId?.invoke(position, data!![position]) ?: super.getItemId(position)
    }

}