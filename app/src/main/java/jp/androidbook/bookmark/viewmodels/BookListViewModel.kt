package jp.androidbook.bookmark.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import jp.androidbook.bookmark.Event
import jp.androidbook.bookmark.data.db.BookDbRepository

class BookListViewModel constructor(private val repository: BookDbRepository) : ViewModel() {

    private val _openTaskEvent = MutableLiveData<Event<Int>>()
    val openTaskEvent: LiveData<Event<Int>> = _openTaskEvent


    val isEmpty: LiveData<Boolean>
        get() = Transformations.map(bookLists) { it.isEmpty() }

    val bookLists = repository.getBookLists()

    fun openBook(id: Int) {
        _openTaskEvent.value = Event(id)
    }
}
