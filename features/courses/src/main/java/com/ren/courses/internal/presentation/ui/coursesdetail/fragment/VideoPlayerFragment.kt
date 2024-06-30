package com.ren.courses.internal.presentation.ui.coursesdetail.fragment

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.ren.courses.R
import com.ren.courses.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment : Fragment(R.layout.fragment_video_player) {

    private val binding by viewBinding(FragmentVideoPlayerBinding::bind)
    private lateinit var player: ExoPlayer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        getArgs()
    }

    private fun getArgs() {
        val args: VideoPlayerFragmentArgs by navArgs()

        args.video?.let { video ->
            setVideo(video)
        }
    }

    private fun setVideo(video: String) = with(binding) {
        val mediaItem = MediaItem.fromUri(video)
        player = ExoPlayer.Builder(requireContext()).build().also {
            it.setMediaItem(mediaItem)
            it.prepare()
            root.player = it
        }
    }

    override fun onStop() {
        super.onStop()
        player.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
        player.stop()
    }
}