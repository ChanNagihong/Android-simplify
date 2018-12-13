package com.nagihong.androidsimplify.edittext

import android.text.Editable
import android.text.TextWatcher

/**
 * Chenyikang
 * 2018 July 30
 */
abstract class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {

    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {

    }
}