package com.matheuslutero.newsapp.auth.domain

enum class BiometricAuthStatus {
    READY,
    NOT_AVAILABLE,
    TEMPORARY_NOT_AVAILABLE,
    AVAILABLE_BUT_NOT_ENROLLED
}

sealed class AuthenticationResult {
    object Success : AuthenticationResult()
    object Failed : AuthenticationResult()
    object Cancelled : AuthenticationResult()
    data class Error(val message: String) : AuthenticationResult()
}
