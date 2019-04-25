package com.nagihong.androidsimplify.recyclerView

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class SimpleAbstractAdapter<DATA> : RecyclerView.Adapter<SimpleViewHolder<DATA>>() {

  var data: List<DATA>? = null

  @LayoutRes
  abstract fun layoutId(): Int

  abstract fun onBind(
    view: View,
    index: Int,
    data: DATA
  )

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) = SimpleViewHolder<DATA>(parent, layoutId()) { view, index, data -> onBind(view, index, data) }

  override fun onBindViewHolder(
    holder: SimpleViewHolder<DATA>,
    position: Int
  ) = holder.bind(data!![position])

  override fun getItemCount() = data?.size ?: 0

}