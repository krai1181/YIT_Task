package com.example.android.yit_task

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.yit_task.model.Item
import com.example.android.yit_task.network.Hit
import com.example.android.yit_task.ui.ImagesAdapter
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


@BindingAdapter("listData")
fun bindRecycler(recyclerView: RecyclerView, data: List<Item>?){
    val adapter = recyclerView.adapter as ImagesAdapter
    adapter.submitList(data)

}

@BindingAdapter("imageList", "selectedElement")
fun bindViewPager(viewPager: ViewPager, data: List<Hit>?, hit: Hit){
    val adapter = viewPager.adapter as ImagePagerAdapter
    data?.let {
        adapter.setImages(data)
        viewPager.currentItem = data.indexOf(hit)
    }

}