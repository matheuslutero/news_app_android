package com.matheuslutero.newsapp.ui.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.databinding.ArticlesFragmentBinding
import java.util.Date
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment(R.layout.articles_fragment) {

    private lateinit var binding: ArticlesFragmentBinding
    private lateinit var adapter: ArticlesAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArticlesFragmentBinding.bind(view)

        configRecyclerView()
        configListeners()
    }

    private fun configRecyclerView() {
        adapter = ArticlesAdapter()
        layoutManager = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> GridLayoutManager(context, 2)
            else -> LinearLayoutManager(context)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun configListeners() {
        binding.container.setOnRefreshListener {
            fetchTopHeadLines()
        }
    }

    private fun fetchTopHeadLines() {
        val articles = listOf(
            Article(
                title = "Article 1",
                description = "Description 1",
                content = "content",
                url = "https://www.google.com",
                urlToImage = "https://picsum.photos/400",
                publishedAt = Date(),
            ),
            Article(
                title = "Article 2",
                description = "Description 2",
                content = "content",
                url = "https://www.google.com",
                urlToImage = "https://picsum.photos/400",
                publishedAt = Date(),
            ),
        )

        viewLifecycleOwner.lifecycleScope.launch {
            binding.container.isRefreshing = true
            delay(2000)
            binding.container.isRefreshing = false
            adapter.submitList(articles)
        }
    }
}
