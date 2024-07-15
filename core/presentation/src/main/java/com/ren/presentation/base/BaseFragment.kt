package com.ren.presentation.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.ren.presentation.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel?>(
    @LayoutRes private val contentLayoutId: Int,
) : Fragment(contentLayoutId) {

    protected abstract val binding: VB
    protected open val viewModel: VM? = null

    protected open fun Fragment.launchWithViewLifecycle(
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(lifecycleState) {
                block()
            }
        }
    }

    protected open fun <T> StateFlow<UIState<T>>.collectUIStateFlow(
        latest: Boolean = false,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        onLoading: (UIState.Loading) -> Unit,
        onError: (UIState.Error) -> Unit,
        onSuccess: (UIState.Success<T>) -> Unit,
    ) {
        launchWithViewLifecycle(lifecycleState = lifecycleState) {
            if (!latest) {
                this@collectUIStateFlow.collect {
                    when (it) {
                        is UIState.Loading -> onLoading(it)
                        is UIState.Error -> onError(it)
                        is UIState.Success -> onSuccess(it)
                    }
                }
            } else {
                this@collectUIStateFlow.collectLatest {
                    when (it) {
                        is UIState.Loading -> onLoading(it)
                        is UIState.Error -> onError(it)
                        is UIState.Success -> onSuccess(it)
                    }
                }
            }
        }
    }

    protected open fun <T> LiveData<UIState<T>>.subscribeAsState(
        onLoading: (UIState.Loading) -> Unit,
        onError: (UIState.Error) -> Unit,
        onSuccess: (UIState.Success<T>) -> Unit,
    ) {
        observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> onLoading(it)
                is UIState.Error -> onError(it)
                is UIState.Success -> onSuccess(it)
            }
        }
    }

    protected open fun <T> LiveData<UIState<T>>.subscribeAsState(
        whileLoading: (Boolean) -> Unit,
        onLoading: (UIState.Loading) -> Unit,
        onError: (UIState.Error) -> Unit,
        onSuccess: (UIState.Success<T>) -> Unit,
    ) {
        observe(viewLifecycleOwner) {
            whileLoading(it is UIState.Loading)
            when (it) {
                is UIState.Loading -> onLoading(it)
                is UIState.Error -> onError(it)
                is UIState.Success -> onSuccess(it)
            }
        }
    }
}