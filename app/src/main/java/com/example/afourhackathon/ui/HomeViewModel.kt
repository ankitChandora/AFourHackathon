package com.example.afourhackathon.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afourhackathon.data.DataRepository
import com.example.afourhackathon.data.model.Post
import com.example.afourhackathon.data.network.ApiResult
import com.example.afourhackathon.util.Event
import kotlinx.coroutines.launch

/**
 * Created by ChandoraAnkit on 17/11/22
 */
class HomeViewModel : ViewModel() {

    private val postsRepository by lazy { DataRepository.getInstance() }

    private val _posts by lazy { MutableLiveData<List<Post>>() }
    val posts: LiveData<List<Post>> = _posts

    private val _showToast = MutableLiveData<Event<String>>()
    val showToast: MutableLiveData<Event<String>> = _showToast

    fun fetchPosts() {
        viewModelScope.launch {
            val result = postsRepository.fetchPosts()
            if (result is ApiResult.Success) {
                _posts.value = result.data
            } else if (result is ApiResult.Error) {
                val message = result.exception.message ?: "Something went wrong"
                _showToast.value = Event(message)
            }
        }
    }
}