package com.ren.menu.internal.presentation.ui.attandences

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentAttendancesBinding
import com.ren.menu.internal.presentation.ui.attandences.adapter.AttandencesAdapter
import com.ren.menu.internal.presentation.ui.attandences.viewmodel.AttendancesViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AttendancesFragment :
    BaseFragment<FragmentAttendancesBinding, AttendancesViewModel>(R.layout.fragment_attendances) {

    override val binding by viewBinding(FragmentAttendancesBinding::bind)
    override val viewModel by viewModels<AttendancesViewModel>()
    private val attandencesAdapter = AttandencesAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        initialize()
    }

    private fun initialize() {
        subscribeToAttandences()
        binding.recAttendances.adapter = attandencesAdapter
    }

    private fun subscribeToAttandences() {
        viewModel.attandencesState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("news", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("news", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    attandencesAdapter.submitList(it)
                    it.map { attandences ->
                        binding.tvNumber.text = attandences.user.totalAttendances.toString()
                    }
                }
            }
        )
    }

    private fun setOnClickListeners() = with(binding) {
        rbLessons.setOnClickListener {
            findNavController().navigate(R.id.action_attendancesFragment_to_lessonsFragment)
        }
    }
}