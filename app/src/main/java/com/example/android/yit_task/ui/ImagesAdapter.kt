package com.example.android.yit_task.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.model.Hit

class ImagesAdapter(private val clickListener: OnClickListener) :
    PagingDataAdapter<Hit, RecyclerView.ViewHolder>(HIT_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsPropertyViewHolder {
        return HitsPropertyViewHolder.create(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hitProperty = getItem(position)
        if (hitProperty != null) {
            holder.itemView.setOnClickListener {
                clickListener.onClick(hitProperty)
            }

            (holder as HitsPropertyViewHolder).bind(hitProperty)
        }

    }

    class OnClickListener(val clickListener: (hitProperty: Hit) -> Unit) {
        fun onClick(hitProperty: Hit) = clickListener(hitProperty)
    }


    companion object {
        private val HIT_COMPARATOR = object : DiffUtil.ItemCallback<Hit>() {
            override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
                return oldItem.imageId == newItem.imageId
            }

        }
    }
}

