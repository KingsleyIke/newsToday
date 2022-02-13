package com.kings.newstoday.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kings.newstoday.R
import com.kings.newstoday.databinding.FragmentArticleBinding
import com.kings.newstoday.databinding.FragmentDetailsBinding
import com.kings.newstoday.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.articles_card_view.view.*
import kotlinx.android.synthetic.main.fragment_details.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    val args: DetailsFragmentArgs  by navArgs()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = _binding!!.root

        binding.title.text = args.article.title
        binding.abstractBody.text = args.article.abstract

        try {
            Glide.with(this).load(args.article.media[0].media_metadata[0].url).centerCrop().into(binding.image)
        } catch (e: Exception) {
            e.printStackTrace()

        }

//        webview.apply {
//            webViewClient = WebViewClient()
//            loadUrl(args.article.url)
//        }


            return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}