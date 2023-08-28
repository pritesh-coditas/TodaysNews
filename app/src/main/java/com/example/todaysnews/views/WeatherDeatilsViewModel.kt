package com.example.weatherapp.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repositories.ApiRepository
import com.example.weatherapp.utils.Resource
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherDeatilsViewModel @Inject constructor (private val apiRepository: ApiRepository) :
    ViewModel() {
    private val _weatherDetailsLiveData = MutableLiveData<Resource<WeatherResponse>>()
    private val weatherDetailsLiveData: LiveData<Resource<WeatherResponse>> = _weatherDetailsLiveData
    fun getWeather(accessKey:String,query:String): LiveData<Resource<WeatherResponse>> {
        viewModelScope.launch {
            _weatherDetailsLiveData.postValue(Resource.Loading())
            try {
                val productList = apiRepository.getProductList(accessKey,query)
                if (productList.body() != null) {
                    _weatherDetailsLiveData.postValue(Resource.Success(productList.body()))
                }
            } catch (e: Exception) {
                Log.d(TAG, "getWeather: ${e.printStackTrace()}")
                _weatherDetailsLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return weatherDetailsLiveData
    }
}