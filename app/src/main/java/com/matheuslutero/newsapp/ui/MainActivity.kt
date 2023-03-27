package com.matheuslutero.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureNavController()
    }

    private fun configureNavController() {
        navController = findNavController(binding.navHostFragment.id)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.authenticationFragment) {
                supportActionBar?.hide()
            } else {
                supportActionBar?.show()
            }
        }

        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(R.id.articlesFragment).build()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun findNavController(viewId: Int) =
        (supportFragmentManager.findFragmentById(viewId) as? NavHostFragment)?.navController
            ?: Navigation.findNavController(this, viewId)
}
