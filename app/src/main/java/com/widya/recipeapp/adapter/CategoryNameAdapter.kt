package com.widya.recipeapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.widya.recipeapp.R
import com.widya.recipeapp.activity.CategoryActivity
import com.widya.recipeapp.model.Category

class CategoryNameAdapter(var listCategory: List<Category?>?) : RecyclerView.Adapter<CategoryNameAdapter.MyViewHolder>(){

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameCategory = itemView.findViewById<TextView>(R.id.tv_name_category)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ):MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_category,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.tvNameCategory.text = listCategory?.get(position)?.nameCategory

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
   Toast.makeText(context,listCategory?.get(position)?.nameCategory, Toast.LENGTH_SHORT).show()

            val i = Intent(context, CategoryActivity::class.java)
            i.putExtra("DTL",listCategory?.get(position))
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return listCategory!!.size
    }

}