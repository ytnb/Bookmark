package jp.androidbook.bookmark.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createBook(bookEntity: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun findAll(): LiveData<List<BookEntity>>

    @Query("SELECT * FROM BookEntity WHERE id = :id")
    fun findId(id: Int): LiveData<BookEntity>

    @Query("DELETE FROM BookEntity WHERE id = :id")
    fun deleteId(id: Int)
}
