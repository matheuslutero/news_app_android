package com.matheuslutero.newsapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.databinding.ArticleDetailFragmentBinding

class ArticleDetailFragment : Fragment(R.layout.article_detail_fragment) {

    private val args: ArticleDetailFragmentArgs by navArgs()
    private val viewModel: ArticleDetailViewModel by viewModels()

    private lateinit var binding: ArticleDetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArticleDetailFragmentBinding.bind(view)

        if (savedInstanceState == null) {
            initViewModel()
        }
        configViews()
    }

    private fun initViewModel() {
        viewModel.init(args.article)
    }

    private fun configViews() {
        viewModel.apply {
            with(binding.titleTextView) {
                isGone = title.isNullOrBlank()
                text = title
            }
            with(binding.articleImageView) {
                isGone = urlToImage.isNullOrBlank()
                if (!urlToImage.isNullOrBlank()) load(urlToImage)
            }
            with(binding.publishedAtTextView) {
                isGone = publishedAt == null
                text = publishedAt?.toString()
            }
            with(binding.descriptionTextView) {
                isGone = description.isNullOrBlank()
                text = description
            }
            with(binding.contentTextView) {
                isGone = content.isNullOrBlank()
                text = content
            }
        }
    }
}
