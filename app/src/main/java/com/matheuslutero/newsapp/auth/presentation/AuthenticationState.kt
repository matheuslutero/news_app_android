package com.matheuslutero.newsapp.auth.presentation

import com.matheuslutero.newsapp.auth.domain.AuthenticationResult
import com.matheuslutero.newsapp.auth.domain.BiometricAuthStatus

data class AuthenticationState(
    val biometricStatus: BiometricAuthStatus? = null,
    val isAuthenticating: Boolean = false,
    val authenticationResult: AuthenticationResult? = null
)
