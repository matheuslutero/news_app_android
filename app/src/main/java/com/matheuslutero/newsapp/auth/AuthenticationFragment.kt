package com.matheuslutero.newsapp.auth

import android.os.Bundle
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.databinding.AuthenticationFragmentBinding

class AuthenticationFragment : Fragment(R.layout.authentication_fragment) {

    private lateinit var binding: AuthenticationFragmentBinding
    private lateinit var biometricManager: BiometricManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AuthenticationFragmentBinding.bind(view)

        iniViews()
        initBiometric()
    }

    private fun iniViews() {
        binding.authView.isVisible = false
        binding.signInButton.setOnClickListener {
            binding.authView.isVisible = false
            authenticate()
        }
    }

    private fun initBiometric() {
        biometricManager = BiometricManager.from(requireContext())

        if (!canAuthenticate()) {
            navigateToArticles()
            return
        }

        authenticate()
    }

    private fun canAuthenticate(): Boolean = biometricManager.canAuthenticate(
        BiometricManager.Authenticators.BIOMETRIC_STRONG
    ) == BiometricManager.BIOMETRIC_SUCCESS

    private fun authenticate() {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_title))
            .setSubtitle(getString(R.string.biometric_subtitle))
            .setNegativeButtonText(getString(R.string.cancel_button))
            .build()

        val executor = ContextCompat.getMainExecutor(requireContext())

        val biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    navigateToArticles()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    binding.authView.isVisible = true
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }

    private fun navigateToArticles() {
        requireView().findNavController().navigate(
            AuthenticationFragmentDirections.actionAuthenticationFragmentToArticlesFragment()
        )
    }
}
