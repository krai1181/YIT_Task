package com.example.android.yit_task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.databinding.ImageItemBinding
import com.example.android.yit_task.model.Hit



class HitsPropertyViewHolder(private var binding: ImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(hitProperty: Hit) {
        binding.property = hitProperty
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): HitsPropertyViewHolder {
            return HitsPropertyViewHolder(ImageItemBinding.inflate(LayoutInflater.from(parent.context)))
        }
    }

}