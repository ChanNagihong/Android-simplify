package com.nagihong.androidsimplify.recyclerView.expandable

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.nagihong.androidsimplify.recyclerView.BaseBindingViewHolder

class SpaceSection<BINDING : ViewDataBinding>(
  private val layoutId: Int,
  private val onBind: (BINDING, Int) -> Unit,
  var count: Int = 1,
  private val type: Int = layoutId
) : BaseSection() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ) =
    BaseBindingViewHolder<BINDING, Any?>(parent, layoutId)

  override fun onBind(
    holder: RecyclerView.ViewHolder,
    position: Int
  ) {
    @Suppress("UNCHECKED_CAST")
    val simpleHolder = holder as BaseBindingViewHolder<BINDING, Any?>
    onBind.invoke(simpleHolder.binding, position)
  }

  override fun count() = count

  override fun type() = type
}