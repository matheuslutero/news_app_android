package com.matheuslutero.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheuslutero.newsapp.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
