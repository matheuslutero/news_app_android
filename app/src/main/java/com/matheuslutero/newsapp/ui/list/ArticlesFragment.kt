package com.matheuslutero.newsapp.ui.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheuslutero.newsapp.R
import com.matheuslutero.newsapp.core.model.Resource
import com.matheuslutero.newsapp.databinding.ArticlesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.articles_fragment) {

    private val viewModel: ArticlesViewModel by viewModels()

    private lateinit var binding: ArticlesFragmentBinding
    private lateinit var adapter: ArticlesAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArticlesFragmentBinding.bind(view)

        initViewModel()
        configRecyclerView()
        configListeners()
    }

    private fun initViewModel() {
        viewModel.listData.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.Success -> {
                    binding.container.isVisible = true
                    binding.container.isRefreshing = false
                    adapter.submitList(it.data)
                }
                Resource.Status.Failure -> {
                    binding.container.isVisible = false
                    binding.container.isRefreshing = false
                }
                Resource.Status.Loading -> {
                    binding.container.isVisible = true
                    binding.container.isRefreshing = true
                }
            }
        }
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
            viewModel.fetchTopHeadLines()
        }
    }
}
