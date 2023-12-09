package com.widya.recipeapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.widya.recipeapp.R
import kotlinx.android.synthetic.main.home_fragment.*

class BookmarkFragment : Fragment() {


    lateinit var listBookmark: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {

        val view: View = inflater.inflate(R.layout.item_bookmark, container, false)
        listBookmark = view.findViewById(R.id.list_bookmark)

        return view
    }


}