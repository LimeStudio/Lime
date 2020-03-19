package com.moi.lime.binding

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("visibleInvisible")
    fun visibleInvisible(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(appCompatImageView: AppCompatImageView, url: String?) {
        if (url.isNullOrEmpty()) return
        Glide.with(appCompatImageView).load(url).into(appCompatImageView)
    }

    @JvmStatic
    @BindingAdapter("viewPagerAdapter")
    fun setViewPagerAdapter(viewPager: ViewPager, adapter: PagerAdapter?) {
        viewPager.adapter = adapter
    }

    @JvmStatic
    @BindingAdapter("textColorInt")
    fun setTextColorInt(textView: TextView, textColorInt: Int) {
        textView.setTextColor(textColorInt)
    }

    @JvmStatic
    @BindingAdapter("backgroundColorInt")
    fun setTextColorInt(view: View, textColorInt: Int) {
        view.setBackgroundColor(textColorInt)
    }

    @JvmStatic
    @BindingAdapter("iconColorInt")
    fun setTextColorInt(view: AppCompatImageView, textColorInt: Int) {
        view.setColorFilter(textColorInt)
    }

}
