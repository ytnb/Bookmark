package jp.androidbook.bookmark.viewmodels

import android.arch.lifecycle.ViewModel
import jp.androidbook.bookmark.data.db.BookDbRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BookListDetailViewModel(private val repository: BookDbRepository, id: Int): ViewModel() {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val book = repository.getBook(id)

    fun deleteBook(id: Int) {
        viewModelScope.launch {
            repository.deleteBook(id)
        }
    }
}
