package com.onedeveloper.populartweets.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.onedeveloper.populartweets.R
import com.onedeveloper.populartweets.modals.ResponseTweet
import com.onedeveloper.populartweets.viewmodels.DetailViewModel

@Composable
fun DetailScreen() {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val tweets: State<List<ResponseTweet>> = detailViewModel.tweets.collectAsState()


    LazyColumn(content = {
        items(tweets.value) {
            TweetListItem(tweet = it.text!!)
        }
    })
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), border = BorderStroke(1.dp, Color(0xFFCCCCCC)), content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontStyle = FontStyle(R.font.montserrat_regular)

            )

        })
}