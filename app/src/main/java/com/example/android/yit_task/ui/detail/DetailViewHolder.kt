package com.example.android.yit_task.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.yit_task.databinding.SliderImageItemBinding
import com.example.android.yit_task.network.Hit

class DetailViewHolder(private var binding: SliderImageItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(hitProperty: Hit) {
        binding.hit = hitProperty
        binding.executePendingBindings()
    }

    companion object{
        fun create(parent: ViewGroup):DetailViewHolder{
            return DetailViewHolder(SliderImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

}