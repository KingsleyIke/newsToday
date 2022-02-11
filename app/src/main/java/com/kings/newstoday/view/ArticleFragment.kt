package com.kings.newstoday.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kings.newstoday.R
import com.kings.newstoday.data.models.Result
import com.kings.newstoday.databinding.FragmentArticleBinding
import com.kings.newstoday.main.MainViewModel
import com.kings.newstoday.utils.ArticleAdapter
import com.kings.newstoday.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var resultLists: List<Result>

    lateinit var articleAdapter: ArticleAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = _binding!!.root

        setupRecyclerView()

        viewModel.articleList.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { articleResponse ->

                        articleAdapter.differ.submitList(articleResponse.results)
                        articleAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

            articleAdapter.setOnClickListener(object :
            ArticleAdapter.OnClickListener {
                override fun onClick(position: Int, result: Result) {

                    val bundle = Bundle().apply {
                        putSerializable("article", result)
                    }
//                    findNavController().navigate(
//                        R.id.action_articleFragment_to_detailsFragment,
//                        bundle
//                    )
                }
            }
            )
        })
        Log.d("List view", "let's test")


        return view
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        articleAdapter = ArticleAdapter()
        binding.recyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext)
        }
    }

    var isLoading = false

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}