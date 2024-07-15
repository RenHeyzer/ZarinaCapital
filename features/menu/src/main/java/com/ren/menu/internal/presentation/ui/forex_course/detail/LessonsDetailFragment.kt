//package com.ren.menu.internal.presentation.ui.forex_course.exercise
//
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
//import by.kirich1409.viewbindingdelegate.viewBinding
//import com.bumptech.glide.Glide
//import com.ren.menu.R
//import com.ren.menu.databinding.FragmentLessonsDetailBinding
//import com.ren.presentation.base.BaseFragment
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class LessonsDetailFragment :
//    BaseFragment<FragmentLessonsDetailBinding, LessonsDetailViewModel>(R.layout.fragment_lessons_detail) {
//
//    override val binding by viewBinding(FragmentLessonsDetailBinding::bind)
//    override val viewModel by viewModels<LessonsDetailViewModel>()
//    private val args by navArgs<LessonsDetailFragmentArgs>()
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initialize()
//    }
//
//    private fun initialize() {
//        endPoint = args.idlessons
//        subscribeToLessons()
//        setOnCLickListeners()
//    }
//
//    private fun subscribeToLessons() {
//        viewModel.lessonsState.collectUIStateFlow(
//            onLoading = { loading ->
//                Log.d("courses", loading.toString())
//            }, onError = { error ->
//                error.message?.let { Log.e("courses", it) }
//            }, onSuccess = { success ->
//                success.data?.let { model ->
//                    binding.tvLesson.text = model.id.toString()
//                    binding.tvStartTime.text = model.startTime
//                    binding.tvEndTime.text = model.endTime
//                    binding.tvName.text = model.name
//                    model.homeworks?.map {
//                        binding.tvDzz.text = it.id.toString()
//                        binding.tvTime.text = it.deadline
//                        binding.textView3.text = it.description
//                        Glide.with(binding.imHomeWorks.context).load(it.homework)
//                            .into(binding.imHomeWorks)
//                    }
//                }
//            })
//    }
//
//    private fun setOnCLickListeners() {
//        binding.rbVisit.setOnClickListener {
//            findNavController().navigate(R.id.action_lessonsDetailFragment_to_attendancesFragment)
//        }
//    }
//
//    companion object {
//        var endPoint = 0
//    }
//}