package com.widya.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.widya.recipeapp.R
import com.widya.recipeapp.adapter.FoodAdapter
import com.widya.recipeapp.model.ApiConfig
import com.widya.recipeapp.model.Category
import com.widya.recipeapp.model.ResponseFood
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        category()
        refresh()

    }

    private fun refresh() {
        refresh.setOnRefreshListener{
            category()
            refresh.isRefreshing = false
        }
    }

    private fun category() {
        val dataCategory = intent.getParcelableExtra<Category>("DTL")
        if(dataCategory !=null){
            val category_id = dataCategory?.id
            tv_category_food.text = dataCategory.nameCategory
            getPercategory(category_id)
        }
    }


    private fun getPercategory(category: Int?) {
        if (category != null) {
            ApiConfig.instanceRetrofit.getPerCategory(category).enqueue(object :
                Callback<ResponseFood> {
                override fun onResponse(call: Call<ResponseFood>, response: Response<ResponseFood>) {
                    if (response.isSuccessful){
                        val responseFood = response.body()
                        val category = responseFood?.data
                        val foodAdapter = FoodAdapter(category)

                        rv_per_category.apply {
                            layoutManager = GridLayoutManager(this@CategoryActivity,2)
                            setHasFixedSize(true)
                            adapter = foodAdapter
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseFood>, t: Throwable) {
                    Toast.makeText(this@CategoryActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}