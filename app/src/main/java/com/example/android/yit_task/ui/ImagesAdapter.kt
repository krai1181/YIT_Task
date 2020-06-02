package com.example.android.yit_task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.databinding.ImageItemBinding
import com.example.android.yit_task.network.Hit

class ImagesAdapter(private  val clickListener: OnClickListener) : ListAdapter<Hit, ImagesAdapter.HitsPropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
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

    class OnClickListener(val clickListener:(hitProperty: Hit) -> Unit){
        fun onClick(hitProperty: Hit) = clickListener(hitProperty)
    }


    class HitsPropertyViewHolder(private var binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hitProperty: Hit) {
            binding.property = hitProperty
            binding.image.layoutParams.height = hitProperty.previewHeight
            binding.image.layoutParams.width = hitProperty.previewWidth
            binding.executePendingBindings()
        }

    }
}