package com.matheuslutero.newsapp.auth.presentation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheuslutero.newsapp.auth.data.BiometricAuthManager
import com.matheuslutero.newsapp.auth.domain.AuthenticationResult
import com.matheuslutero.newsapp.auth.domain.BiometricAuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val biometricAuthManager: BiometricAuthManager,
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(AuthenticationState())
    val stateFlow = _stateFlow.asStateFlow()

    init {
        observeAuthenticationResults()
    }

    fun checkBiometricAvailability(activity: FragmentActivity) {
        val status = biometricAuthManager.getBiometricAuthStatus(activity)
        _stateFlow.update { it.copy(biometricStatus = status) }
        
        if (status != BiometricAuthStatus.READY) {
            _stateFlow.update { 
                it.copy(authenticationResult = AuthenticationResult.Success) 
            }
        }
    }

    fun authenticate(
        activity: FragmentActivity,
        title: String,
        subtitle: String,
        negativeButtonText: String
    ) {
        _stateFlow.update { 
            it.copy(
                isAuthenticating = true, 
                authenticationResult = null
            ) 
        }
        
        biometricAuthManager.authenticate(
            activity = activity,
            title = title,
            subtitle = subtitle,
            negativeButtonText = negativeButtonText
        )
    }

    private fun observeAuthenticationResults() {
        viewModelScope.launch {
            biometricAuthManager.authenticationResult.collect { result ->
                _stateFlow.update { 
                    it.copy(
                        isAuthenticating = false,
                        authenticationResult = result
                    ) 
                }
            }
        }
    }
}
