package com.nagihong.androidsimplify.recyclerView

import android.support.v7.widget.RecyclerView

class SimpleAdapterDataObserver(private val callback: (() -> Unit)) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        super.onChanged()
        callback.invoke()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        super.onItemRangeRemoved(positionStart, itemCount)
        callback.invoke()
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount)
        callback.invoke()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        callback.invoke()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        super.onItemRangeChanged(positionStart, itemCount)
        callback.invoke()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        super.onItemRangeChanged(positionStart, itemCount, payload)
        callback.invoke()
    }
}