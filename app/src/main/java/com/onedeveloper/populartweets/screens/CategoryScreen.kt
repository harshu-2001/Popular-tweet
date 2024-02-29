package com.onedeveloper.populartweets.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.onedeveloper.populartweets.R
import com.onedeveloper.populartweets.viewmodels.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    if (categories.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Please wait ....",
                    style = MaterialTheme.typography.headlineSmall,
                    fontStyle = FontStyle(R.font.montserrat_regular)
                )
                CircularProgressIndicator(color = Color.Cyan)
            }
        }
    } else {
        Text(
            text = "Tweets \uD835\uDD4F \uD83D\uDCC3",
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
            fontStyle = FontStyle(R.font.montserrat_regular),
            modifier = Modifier.padding(10.dp)
        )

        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {

            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Choose Category",
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineSmall,
                    fontStyle = FontStyle(R.font.montserrat_regular)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    items(categories.value.distinct()) {
                        CategoryItem(category = it, onClick)
                    }
                }
            }
        }

    }

}


@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick(category) }
            .size(160.dp)
            .clip(RoundedCornerShape(10.dp))
            .paint(painter = painterResource(R.drawable.bg), contentScale = ContentScale.Crop)
            .border(1.dp, Color(0xFFE6EBEF)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(0.dp, 20.dp),
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
            fontStyle = FontStyle(R.font.montserrat_regular)
        )
    }
}