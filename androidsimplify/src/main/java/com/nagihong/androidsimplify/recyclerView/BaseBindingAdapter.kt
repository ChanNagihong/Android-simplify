package com.nagihong.androidsimplify.recyclerView

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

abstract class BaseBindingAdapter<BINDING: ViewDataBinding, DATA>: RecyclerView.Adapter<BaseBindingViewHolder<BINDING, DATA>>()