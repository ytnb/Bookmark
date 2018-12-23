package jp.androidbook.bookmark.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import jp.androidbook.bookmark.data.db.BookDbRepository

class BookListViewModel constructor(private val repository: BookDbRepository) : ViewModel() {

    val isEmpty: LiveData<Boolean>
        get() = Transformations.map(bookLists) { it.isEmpty() }

    val bookLists = repository.getBookLists()
}
