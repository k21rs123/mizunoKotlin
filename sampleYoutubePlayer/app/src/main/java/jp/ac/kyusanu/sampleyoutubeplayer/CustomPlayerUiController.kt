package jp.ac.kyusanu.sampleyoutubeplayer

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class CustomPlayerUiController(
    customPlayerUi: View,
    private val youTubePlayer: YouTubePlayer
    ) : AbstractYouTubePlayerListener() {

    private val playerTracker: YouTubePlayerTracker = YouTubePlayerTracker()
    // private var panel: View

    init {
        youTubePlayer.addListener(playerTracker)

        val playPauseButton = customPlayerUi.findViewById<Button>(R.id.play_pause_button)
        val stop = customPlayerUi.findViewById<ImageView>(R.id.stop)
        stop.isVisible = false

        playPauseButton.setOnClickListener { view ->
            if (playerTracker.state == PlayerConstants.PlayerState.PLAYING) {
                youTubePlayer.pause()
                stop.isVisible = true
            } else {
                youTubePlayer.play()
                stop.isVisible = false
            }

        }
    }
}