package jp.androidbook.bookmark.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

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
