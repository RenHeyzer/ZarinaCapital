package com.ren.menu.internal.presentation.ui.fragments.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentVideoBinding
import com.ren.menu.internal.presentation.ui.fragments.video.viewmodel.VideoViewModel
import com.ren.presentation.base.BaseFragment

class VideoFragment :
    BaseFragment<FragmentVideoBinding, VideoViewModel>(R.layout.fragment_video) {

    override val binding by viewBinding(FragmentVideoBinding::bind)
    override val viewModel by viewModels<VideoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){

    }
}
