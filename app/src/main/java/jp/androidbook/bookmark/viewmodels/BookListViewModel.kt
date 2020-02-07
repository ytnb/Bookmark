package jp.androidbook.bookmark.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import jp.androidbook.bookmark.data.db.BookDbRepository

class BookListViewModel constructor(private val repository: BookDbRepository) : ViewModel() {

    val isEmpty: LiveData<Boolean>
        get() = Transformations.map(bookLists) { it.isEmpty() }

    val bookLists = repository.getBookLists()
}
