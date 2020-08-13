package com.example.android.yit_task.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.android.yit_task.databinding.SliderImageItemBinding
import com.example.android.yit_task.model.Hit

class ImagePagerAdapter : PagerAdapter() {

    private var images = emptyList<Hit>()

    fun setImages(hits: List<Hit>) {
        images = hits
       notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object`

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = SliderImageItemBinding.inflate(LayoutInflater.from(container.context))
        val imageView = binding.root as ImageView

        if (imageView.parent != null) {
            (imageView.parent as ViewGroup).removeView(imageView)
        }
        container.addView(imageView)
        binding.hit = images[position]
        binding.executePendingBindings()
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }



}
