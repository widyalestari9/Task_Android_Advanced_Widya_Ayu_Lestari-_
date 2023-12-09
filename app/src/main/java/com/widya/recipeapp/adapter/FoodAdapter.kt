package com.widya.recipeapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.widya.recipeapp.R
import com.widya.recipeapp.activity.DetailActivity
import com.widya.recipeapp.model.DataItem

class FoodAdapter(var listFood: List<DataItem?>?) : RecyclerView.Adapter<FoodAdapter.MyViewHolder>(){
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val imgFood = itemview.findViewById<ImageView>(R.id.img_food)
//        val tvNameFood = itemview.findViewById<TextView>(R.id.tvTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.tvNameFood.text = listFood?.get(position)?.name
        val img = "http://192.168.33.142/healthy-app/public/storage/"+listFood?.get(position)?.image

        Picasso.get()
            .load(img)
            .placeholder(R.drawable.quotes1)
            .error(R.drawable.ic_home)
            .into(holder.imgFood)

        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("RECIPE", listFood?.get(position))
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        if (listFood != null){
            return listFood!!.size
        }else{
            return 0
        }
    }
}
