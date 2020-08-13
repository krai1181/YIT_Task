package com.example.android.yit_task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.databinding.ImageItemBinding
import com.example.android.yit_task.model.Item

class ImagesAdapter(private  val clickListener: OnClickListener) : ListAdapter<Item, ImagesAdapter.HitsPropertyViewHolder>(DiffCallback) {


    val spanSizeLookup: GridLayoutManager.SpanSizeLookup by lazy {
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return getItem(position).columns
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).columns
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.imageId == newItem.imageId
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitsPropertyViewHolder {
        return HitsPropertyViewHolder(ImageItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HitsPropertyViewHolder, position: Int) {
        val hitProperty = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(hitProperty)
        }
        holder.bind(hitProperty)

    }

    class OnClickListener(val clickListener:(hitProperty: Item) -> Unit){
        fun onClick(hitProperty: Item) = clickListener(hitProperty)
    }


    class HitsPropertyViewHolder(private var binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hitProperty: Item) {
            binding.property = hitProperty
            binding.executePendingBindings()
        }

    }
}