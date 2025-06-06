package com.matheuslutero.newsapp.auth.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matheuslutero.newsapp.auth.domain.AuthenticationResult
import com.matheuslutero.newsapp.auth.domain.BiometricAuthStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationScreen(
    onAuthenticationSuccess: () -> Unit,
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val activity = remember {
        context as? FragmentActivity
            ?: throw IllegalStateException("Activity must be a FragmentActivity")
    }
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(activity) {
        viewModel.checkBiometricAvailability(activity)
    }

    LaunchedEffect(state.authenticationResult) {
        if (state.authenticationResult is AuthenticationResult.Success) {
            onAuthenticationSuccess()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        when (state.biometricStatus) {
            BiometricAuthStatus.READY -> {
                AuthenticationBiometricView(
                    state = state,
                    activity = activity,
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            }

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                }
            }
        }
    }
}
