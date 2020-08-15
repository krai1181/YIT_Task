package com.example.android.yit_task

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.yit_task.model.Hit
import com.example.android.yit_task.ui.detail.ImagePagerAdapter

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, url:String?)
{
    url?.let{
        val imageUrl =  Uri.parse(it).buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(RequestOptions()
                .placeholder(R.drawable.placeholder)
            )
            .into(imageView)
    }
}


@BindingAdapter("imageList", "selectedElement")
fun bindViewPager(viewPager: ViewPager2, data: List<Hit>?, hit: Hit){
    val adapter = viewPager.adapter as ImagePagerAdapter
    data?.let {
        adapter.submitList(data)
        viewPager.currentItem = data.indexOf(hit)
    }

}