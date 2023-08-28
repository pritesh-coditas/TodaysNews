package com.example.todaysnews.views

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.*
import com.example.todaysnews.model.ArticleResponse
import com.example.todaysnews.repositories.ApiRepository
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor (private val apiRepository: ApiRepository) :
    ViewModel() {
    private val _newsDetailsLiveData = MutableLiveData<Resource<ArticleResponse>>()
    private val newsDetailsLiveData: LiveData<Resource<ArticleResponse>> = _newsDetailsLiveData
    fun getWeather(): LiveData<Resource<ArticleResponse>> {
        viewModelScope.launch {
            _newsDetailsLiveData.postValue(Resource.Loading())
            try {
                val productList = apiRepository.getNewsList()
                if (productList.body() != null) {
                    _newsDetailsLiveData.postValue(Resource.Success(productList.body()))
                }
            } catch (e: Exception) {
                Log.d(TAG, "getNews: ${e.printStackTrace()}")
                _newsDetailsLiveData.postValue(Resource.Error(e.message.toString()))
            }
        }
        return newsDetailsLiveData
    }
}