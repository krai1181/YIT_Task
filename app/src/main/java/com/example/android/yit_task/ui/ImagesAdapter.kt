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

            val hitsList = mutableListOf<Hit>()
            for (i in 0 until itemCount) {
                val temp = getItem(i)
                if (temp != null) {
                    hitsList.run { add(i, temp) }
                }
            }

            holder.itemView.setOnClickListener {
                clickListener.onClick(hitProperty, hitsList.toList())
            }

            (holder as HitsPropertyViewHolder).bind(hitProperty)
        }

    }

    class OnClickListener(val clickListener: (hitProperty: Hit, hitsList: List<Hit>) -> Unit) {
        fun onClick(hitProperty: Hit, hitsList: List<Hit>) = clickListener(hitProperty, hitsList)
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

