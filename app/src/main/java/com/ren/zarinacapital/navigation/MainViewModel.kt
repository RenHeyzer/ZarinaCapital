package com.ren.zarinacapital.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ren.datastore.api.TokenManager
import com.ren.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : BaseViewModel() {

    private val _tokenState = MutableLiveData<String?>()
    val tokenState: LiveData<String?> = _tokenState

    init {
        viewModelScope.launch {
            tokenManager.getTokens().collectLatest { tokens ->
                _tokenState.value = tokens.refreshToken
            }
        }
    }
}