package com.example.todaysnews.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.todaysnews.R
import com.example.newsapp.utils.Resource
import com.example.todaysnews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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

                        response.data?.let {
                            /*      binding.articleTitle.text = getString(R.string.articel_title)
                                  binding.articleAuthor.text = getString(R.string.author)
                                  binding.articleDescription.text = getString(R.string.articel_description)
                                  binding.articlePublishedAt.text = getString(R.string.publish_date)
                                  binding.articleContent.text = getString(R.string.articel_content)*/
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}