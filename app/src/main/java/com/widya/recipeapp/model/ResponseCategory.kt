package com.widya.recipeapp.model

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

    @field:SerializedName("data")
	val data: List<Category?>? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Int? = null
)