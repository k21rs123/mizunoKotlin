package com.example.viewpageryoutube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpageryoutube.databinding.ActivityMainBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewPager2.setOnClickListener{

        }

        val list = mutableListOf("7zDeburQ3dE","YUO18E6gQBY","IFu32qORBLA","D8RuJS9_fK4","jVFidAKF9cE","m-4f8PO1QbI","jjJ2welSE9M")
        binding.viewPager2.also {
            it.adapter = ViewPagerAdapter(binding,list)
            it.offscreenPageLimit = 2
            it.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.youtubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                        override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(list[position], 0f)
                        }
                    })
                }
            })
        }
    }
}