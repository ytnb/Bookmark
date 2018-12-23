package jp.androidbook.bookmark.data.db

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookDbRepository private constructor(private val dao: BookDao) {

    fun getBookLists() = dao.findAll()

    fun getBook(id: Int) = dao.findId(id)

    suspend fun createBook(book: BookEntity) {
        withContext(Dispatchers.IO) {
            dao.createBook(book)
        }
    }

    suspend fun deleteBook(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteId(id)
        }
    }

    companion object {
        private var instance: BookDbRepository? = null
        fun getInstance(dao: BookDao): BookDbRepository {
            return instance ?: synchronized(this) {
                instance
                    ?: BookDbRepository(dao).also { instance = it }
            }
        }
    }
}
