package com.example.android.yit_task.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.yit_task.network.Hit

class ImagePagerAdapter : ListAdapter<Hit,DetailViewHolder>(DiffCallback) {

    private var images = emptyList<Hit>()

    /*     fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = SliderImageItemBinding.inflate(LayoutInflater.from(container.context))
        val imageView = binding.root as ImageView

        if (imageView.parent != null) {
            (imageView.parent as ViewGroup).removeView(imageView)
        }
        container.addView(imageView)
        binding.hit = images[position]
        binding.executePendingBindings()
        return imageView
    }*/

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
