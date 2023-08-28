package com.example.todaysnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.todaysnews.R
import com.example.todaysnews.utils.Resource
import com.example.todaysnews.databinding.ActivityMainBinding
import com.example.todaysnews.model.Source
import dagger.hilt.android.AndroidEntryPoint
import java.util.logging.Logger

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherDetailsViewModel: NewsDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNews()
    }

    /**
     * It will get news info.
     */
    private fun getNews() {
        weatherDetailsViewModel.getWeather()
            .observe(this) { response ->
                when (response) {
                    is Resource.Loading -> {
                        Toast.makeText(this, "Loading news", Toast.LENGTH_LONG).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this, "news fetched!!!!", Toast.LENGTH_LONG).show()

                        response.data?.articles.let {
                            val article = it?.get(0)
                            Log.d("Response", "$it")
                            binding.articleTitle.text = article?.title
                            binding.articleAuthor.text = article?.author
                            binding.articleDescription.text =
                                article?.description
                            binding.articlePublishedAt.text = article?.publishedAt
                            binding.articleContent.text = article?.content

                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}