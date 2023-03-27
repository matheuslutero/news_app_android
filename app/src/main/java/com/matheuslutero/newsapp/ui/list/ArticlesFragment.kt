package com.matheuslutero.newsapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.databinding.ArticlesFragmentBinding

class ArticlesFragment : Fragment(R.layout.articles_fragment) {
    private lateinit var binding: ArticlesFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArticlesFragmentBinding.bind(view)
    }
}
