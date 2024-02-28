package com.onedeveloper.populartweets.repository

import com.onedeveloper.populartweets.Tweets
import com.onedeveloper.populartweets.api.TweetsyApi
import com.onedeveloper.populartweets.modals.ResponseTweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetsRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<ResponseTweet>>(emptyList())
    val tweets: StateFlow<List<ResponseTweet>>
        get() = _tweets

    suspend fun getTweets(category: String) {
        val response = tweetsyApi.getTweets("tweets[?(@.category==\"${category}\")]")
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _tweets.emit(response.body()!!)
        }
    }

    suspend fun getCategories() {
        val response = tweetsyApi.getCategories()
        if (response.isSuccessful && response.body().isNullOrEmpty().not()) {
            _categories.emit(response.body()!!)
        }
    }

}