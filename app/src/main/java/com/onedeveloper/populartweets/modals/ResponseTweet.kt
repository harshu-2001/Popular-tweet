package com.onedeveloper.populartweets.modals

import com.google.gson.annotations.SerializedName

data class ResponseTweet(

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
