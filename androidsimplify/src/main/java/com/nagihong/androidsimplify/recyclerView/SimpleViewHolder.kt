package com.nagihong.androidsimplify.recyclerView

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup

open class SimpleViewHolder<DATA>(
  parent: ViewGroup, @LayoutRes layoutId: Int,
  private val func: (View, Int, DATA) -> Unit
) : BaseViewHolder<DATA>(parent, layoutId) {

  override fun bind(data: DATA) {
    super.bind(data)
    func.invoke(itemView, layoutPosition, data)
  }

}