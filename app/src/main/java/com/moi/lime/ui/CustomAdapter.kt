package com.moi.lime.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moi.lime.R
import com.moi.lime.repository.DataBean
import kotlinx.android.synthetic.main.view_custom.view.*

class CustomAdapter : PagedListAdapter<DataBean, CustomAdapter.CustomViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_custom, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: DataBean?) {
            itemView.name.text = item?.name
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataBean>() {
            override fun areItemsTheSame(oldConcert: DataBean,
                                         newConcert: DataBean): Boolean =
                    oldConcert.id == newConcert.id

            override fun areContentsTheSame(oldConcert: DataBean,
                                            newConcert: DataBean): Boolean =
                    oldConcert == newConcert
        }
    }
}