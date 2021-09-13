package com.compasso.bookschange.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BooksDao {
    @Query("SELECT * FROM book")
    suspend fun getAll(): List<Book>

    @Query("SELECT * FROM book WHERE id IN (:bookIds)")
    suspend fun loadAllByIds(bookIds: IntArray): List<Book>

    @Insert
    suspend fun insertAll(vararg books: Book)

    @Delete
    suspend fun delete(book: Book)
}
