package com.example.weatherapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherDeatilsViewModel: WeatherDeatilsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFetch.setOnClickListener {
            getWeather()
        }
    }

    /**
     * It will show the fetched weather info on UI.
     */
    private fun getWeather() {
        weatherDeatilsViewModel.getWeather("5e5f6d5574940554ecd0285a63436427", "India")
            .observe(this) { response ->
                when (response) {
                    is Resource.Loading -> {
                        Toast.makeText(this, "Loading buddy", Toast.LENGTH_LONG).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this, "Weather fetched!!!!", Toast.LENGTH_LONG).show()

                        response.data?.let {
                            binding.txtTemprature.text = getString(
                                R.string.temperature_today_dc_textview,
                                it.current.temperature.toString()
                            )
                            binding.txtHumidity.text = getString(
                                R.string.humidity_today_dc_textview,
                                it.current.humidity.toString()
                            )
                            binding.txtLocation.text = it.location.region
                        }
                    }

                    is Resource.Error -> {
                        Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}