package com.matheuslutero.newsapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.matheuslutero.newsapp.core.extension.toTimeSpanString
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.databinding.ArticlesItemLayoutBinding

class ArticlesAdapter : ListAdapter<Article, ArticlesAdapter.ViewHolder>(diffCallback) {

    inner class ViewHolder(
        val binding: ArticlesItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url &&
                        oldItem.publishedAt == newItem.publishedAt
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArticlesItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)

        holder.binding.apply {
            holder.itemView.apply {
                titleTextView.text = article?.title
                publishedAtTextView.text = article?.publishedAt?.toTimeSpanString(context)

                articleImageView.load(article?.urlToImage) {
                    crossfade(true)
                    crossfade(800)
                }

                setOnClickListener {
                    Navigation.findNavController(it).navigate(
                        ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailFragment(
                            article
                        )
                    )
                }
            }
        }
    }
}
