package com.widya.recipeapp.bookmark

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookmark")
class BookmarkEntity(
    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "calories")
    val calories: String,

    @field:ColumnInfo(name = "image")
    val image: String,

    @field:ColumnInfo(name = "decs")
    val desc: String,

    @field:ColumnInfo(name = "ingredient")
    val ingredient: String,

    @field:ColumnInfo(name = "id")
    @PrimaryKey()
    val id: String

)