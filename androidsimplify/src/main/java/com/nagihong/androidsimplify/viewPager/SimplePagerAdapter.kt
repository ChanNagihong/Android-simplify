package com.nagihong.androidsimplify.viewPager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Chenyikang
 * 2018 October 16
 */
class SimplePagerAdapter(
  fm: FragmentManager,
  private val fragments: List<Fragment>,
  private val titles: List<String>? = null
) : FragmentStatePagerAdapter(fm) {
  override fun getItem(p0: Int) = fragments[p0]

  override fun getCount() = fragments.size

  override fun getPageTitle(position: Int) = titles?.get(position)

}