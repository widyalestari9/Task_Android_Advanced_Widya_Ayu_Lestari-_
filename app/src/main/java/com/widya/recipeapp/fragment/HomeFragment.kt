package com.widya.recipeapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.widya.recipeapp.R
import com.widya.recipeapp.adapter.AdapterSlider
import com.widya.recipeapp.adapter.CategoryNameAdapter
import com.widya.recipeapp.adapter.FoodAdapter
import com.widya.recipeapp.databinding.HomeFragmentBinding
import com.widya.recipeapp.model.ApiConfig
import com.widya.recipeapp.model.ResponseCategory
import com.widya.recipeapp.model.ResponseFood
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var vpSlider: ViewPager
    lateinit var rvFood: RecyclerView
    lateinit var rvNameCategory: RecyclerView
    lateinit var sRLoading: SwipeRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        val view: View = inflater.inflate(R.layout.home_fragment, container, false)

        vpSlider = view.findViewById(R.id.vp_slider)
        rvFood = view.findViewById(R.id.rv_food)
        rvNameCategory = view.findViewById(R.id.rv_name_category)
        sRLoading = view.findViewById(R.id.srl_loading)

        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.quotes1)
        arrSlider.add(R.drawable.quotes2)
        arrSlider.add(R.drawable.quotes3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpSlider.adapter = adapterSlider

        getNameCategory()
        getDataRecipe()
        SRefreshLoading()

        return view
    }

    private fun SRefreshLoading() {
        sRLoading.setOnRefreshListener {
            getNameCategory()
            getDataRecipe()
            sRLoading.isRefreshing = false
        }
    }

    private fun getDataRecipe() {
        ApiConfig.instanceRetrofit.getRecipe().enqueue(object : Callback<ResponseFood> {
            override fun onResponse(call: Call<ResponseFood>, response: Response<ResponseFood>) {
                if (response.isSuccessful) {
                    val responseFood = response.body()
                    val food = responseFood?.data
                    val foodAdapter = FoodAdapter(food)

                    rvFood.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        (layoutManager as LinearLayoutManager).orientation =
                            LinearLayoutManager.HORIZONTAL
                        foodAdapter.notifyDataSetChanged()
                        adapter = foodAdapter
                    }
                }
            }

            override fun onFailure(call: Call<ResponseFood>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun getNameCategory() {
        ApiConfig.instanceRetrofit.getCategoryRecipe().enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(
                call: Call<ResponseCategory>,
                response: Response<ResponseCategory>, ) {
                if (response.isSuccessful) {
                    val responseCategory = response.body()
                    val category = responseCategory?.data
                    val categoryNameAdapter = CategoryNameAdapter(category)
                    rvNameCategory.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(activity)
                        (layoutManager as LinearLayoutManager).orientation =
                            LinearLayoutManager.HORIZONTAL
                        categoryNameAdapter.notifyDataSetChanged()
                        adapter = categoryNameAdapter
                    }


                }
            }

            override fun onFailure(call: Call<ResponseCategory>, t: Throwable) {
                Toast.makeText(activity, t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })
    }
}
