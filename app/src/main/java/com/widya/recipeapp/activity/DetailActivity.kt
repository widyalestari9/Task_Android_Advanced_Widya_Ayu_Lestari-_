package com.widya.recipeapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import com.widya.recipeapp.R
import com.widya.recipeapp.databinding.ActivityDetailBinding
import com.widya.recipeapp.model.Category
import com.widya.recipeapp.model.DataItem


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.tbDetail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        detailRecipe()

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun detailRecipe() {
        val dataDetailRecipe = intent.getParcelableExtra<DataItem>("RECIPE")
        if (dataDetailRecipe != null){

           val img = "http://192.168.33.142/healthy-app/public/storage/"+dataDetailRecipe.image

            Picasso.get()
                .load(img)
                .placeholder(R.drawable.quotes1)
                .error(R.drawable.ic_home)
                .into(binding.imgThumb)

            binding.tvTitle.text = dataDetailRecipe.name
            binding.tvDescIngredients.text = dataDetailRecipe.ingredient
            binding.tvDescCalories.text = dataDetailRecipe.calories
            binding.tvDescInstructions.text = dataDetailRecipe.step
            binding.tvDesc.text =dataDetailRecipe.description

        }
    }
}

