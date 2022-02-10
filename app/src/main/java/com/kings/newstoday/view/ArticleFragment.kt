package com.kings.newstoday.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kings.newstoday.data.models.Model
import com.kings.newstoday.databinding.FragmentArticleBinding
import com.kings.newstoday.main.MainViewModel
import com.kings.newstoday.utils.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: ArticleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = _binding!!.root


        viewModel.getArticlesList()

        adapter = ArticleAdapter(activity!!.applicationContext,  viewModel.articleList)

        binding.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.recyclerView.adapter = adapter




        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}