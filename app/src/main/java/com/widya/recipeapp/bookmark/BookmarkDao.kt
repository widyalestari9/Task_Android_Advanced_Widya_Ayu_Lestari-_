package com.widya.recipeapp.bookmark

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark")
    fun getAllBookmark(): LiveData<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookmark(bookmark: BookmarkEntity)

    @Query("SELECT count(*) FROM bookmark WHERE bookmark.id = :id")
    suspend fun checkBookmark(id: String): Boolean

    @Query("DELETE FROM bookmark WHERE bookmark.id = :id")
    suspend fun deleteBookmark(id: String): Int
}