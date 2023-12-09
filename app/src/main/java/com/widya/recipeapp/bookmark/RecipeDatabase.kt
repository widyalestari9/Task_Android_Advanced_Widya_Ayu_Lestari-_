package com.widya.recipeapp.bookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//cara nyambungin database ke tabel bookmark
@Database(entities = [BookmarkEntity::class], version = 1)
abstract class RecipeDatabase: RoomDatabase(){

    abstract fun bookmarkDao(): BookmarkDao

    companion object{
        @Volatile
        var INSTANCE: RecipeDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): RecipeDatabase?{
            if (INSTANCE == null){
                synchronized(RecipeDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RecipeDatabase::class.java,"recipe_database").build()
                }
            }
            return INSTANCE
        }
    }
}