package com.acious.plabs

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.Constraints
import com.acious.plabs.util.dpToPx

class OrderBookItemBg @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    fun setWidth(widthDP: Float) {
        layoutParams.width = widthDP.toInt()
    }
}