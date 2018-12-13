package com.nagihong.androidsimplify.recyclerView.expandable

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class BaseSection {

  @Suppress("PropertyName")
  protected val NO_COUNT = -1
  private var notify: (() -> Unit)? = null

  abstract fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

  abstract fun onBind(holder: RecyclerView.ViewHolder, position: Int)

  abstract fun count(): Int

  abstract fun type(): Int

  fun attach(notify: (() -> Unit)) {
    this.notify = notify
  }

  fun notifyDataSetChanged() {
    notify?.invoke()
  }

  open fun id(position: Int): Long = -1L

}