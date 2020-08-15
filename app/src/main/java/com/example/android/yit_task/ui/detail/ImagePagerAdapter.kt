package com.example.android.yit_task.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.yit_task.model.Hit


class ImagePagerAdapter : ListAdapter<Hit,DetailViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val hitProperty = getItem(position)
        holder.bind(hitProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.imageId == newItem.imageId
        }

    }


}
