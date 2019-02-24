package com.moi.lime.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class NoScrollViewPager : ViewPager {
    var isScroll = false

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)


    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)   // return true;不行
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (isScroll) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return if (isScroll) {
            super.onTouchEvent(ev)
        } else {
            true
        }
    }
}