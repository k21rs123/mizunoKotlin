package com.example.viewpageryoutube

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.viewpageryoutube.databinding.ActivityMainBinding
import com.example.viewpageryoutube.databinding.ViewPagerBinding

class ViewPagerAdapter(
    private val activityMainBinding: ActivityMainBinding,
    private val list: MutableList<String>
) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    private lateinit var pageBinding: ViewPagerBinding

    inner class PageViewHolder(val binding: ViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val viewPagerBinding: ViewPagerBinding =
            ViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        pageBinding = viewPagerBinding

        return PageViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        // ここでBindingを使用する場合は、holderのbindingを使います

        val videoId = list[holder.absoluteAdapterPosition]

        val thumbnailUrl = "https://img.youtube.com/vi/$videoId/0.jpg"
        Glide.with(holder.binding.imageView.context).load(thumbnailUrl).centerCrop()
            .into(holder.binding.imageView)

        activityMainBinding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Handler(Looper.getMainLooper()).postDelayed({
                    holder.binding.imageView.isVisible =
                        activityMainBinding.viewPager2.currentItem != holder.absoluteAdapterPosition
                }, 500)
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}