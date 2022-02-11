package com.kings.newstoday.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kings.newstoday.R
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.data.models.Result
import kotlinx.android.synthetic.main.articles_card_view.view.*
import java.lang.Exception
import javax.inject.Inject

class ArticleAdapter () : RecyclerView.Adapter<ArticleAdapter.MyViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.e("Adapeter", "Oncreate" )
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.articles_card_view, parent, false))

    }

    private var onItemClickListener: ((Result) -> Unit)? = null

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = differ.currentList[position]
//        val article = resultList[position]
        Log.e("Adapeter", "Bind view" )

        holder.itemView.apply {
            try {
                Glide.with(this).load(article.media[0].media_metadata[0].url).centerCrop().into(iv_article_image)

            } catch (e: Exception) {
                e.printStackTrace()
            }
            tv_title.text = article.title
            tv_brief.text = article.abstract
            Log.e("Adapeter", article.abstract )

        }

            Log.e("Adapeter", article.title)
            Log.e("Adapeter", article.abstract)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
//        return resultList.size
    }

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    fun updateAdapeter () {
        notifyDataSetChanged()
    }
}