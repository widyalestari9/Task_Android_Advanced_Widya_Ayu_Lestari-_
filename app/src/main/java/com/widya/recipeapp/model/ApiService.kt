package com.widya.recipeapp.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("getRecipe")
    fun getRecipe() : Call<ResponseFood>

    @GET("getCategory")
    fun getCategoryRecipe() : Call<ResponseCategory>

    @GET("recipe/categori/{categori}")
    fun getPerCategory(@Path("categori") categori : Int) : Call<ResponseFood>


}

