package com.kings.newstoday.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kings.newstoday.R
import com.kings.newstoday.data.models.Model
import kotlinx.android.synthetic.main.articles_card_view.view.*
import javax.inject.Inject

class ArticleAdapter @Inject constructor (private val context: Context, private var article: Model) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.articles_card_view, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val article = article.results[position]

        if (holder is MyViewHolder) {

            holder.itemView.tv_title.text = article.title
            holder.itemView.tv_brief.text = article.abstract

            Glide.with(context).load(article.media[0].media_metadata[0].url).centerCrop().into(holder.itemView.iv_article_image)
        }
    }

    override fun getItemCount(): Int {
        return article.results.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}