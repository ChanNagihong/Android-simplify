package com.nagihong.androidsimplify.recyclerView.expandable

/**
 * Chenyikang
 * 2018 October 12
 */
abstract class BaseExpandableSection : BaseSection() {

  val types by lazy(LazyThreadSafetyMode.NONE) { collectTypes() }

  abstract fun collectTypes(): List<Int>

  abstract fun typeAt(position: Int): Int

  override fun type() = -1

  abstract fun childCountAt(position: Int): Int

}