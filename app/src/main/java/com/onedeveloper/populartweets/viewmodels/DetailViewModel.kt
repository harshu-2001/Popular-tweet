package com.onedeveloper.populartweets.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedeveloper.populartweets.modals.ResponseTweet
import com.onedeveloper.populartweets.repository.TweetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TweetsRepository,private val savedStateHandle:SavedStateHandle) :ViewModel() {
    val tweets:StateFlow<List<ResponseTweet>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            var category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category)
        }
    }
}