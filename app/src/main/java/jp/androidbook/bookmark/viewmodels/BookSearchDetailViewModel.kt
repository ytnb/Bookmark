package jp.androidbook.bookmark.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jp.androidbook.bookmark.data.Book
import jp.androidbook.bookmark.data.api.GoogleBookClients
import jp.androidbook.bookmark.data.db.BookDbRepository
import jp.androidbook.bookmark.data.db.BookEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BookSearchDetailViewModel(
    private val bookRepository: BookDbRepository,
    private val isbn: String
) : ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    val book: LiveData<Book> = GoogleBookClients.getBookDetail("isbn:$isbn")

    fun createBook() {
        viewModelScope.launch {
            // TODO 重複チェックいれたい
            val b = book.value!!
            val result = BookEntity().also {
                it.title = b.items[0].volumeInfo.title
                it.authors = b.items[0].volumeInfo.authors?.joinToString()
                it.publisher = b.items[0].volumeInfo.publisher
                it.publishedDate = b.items[0].volumeInfo.publishedDate
                it.description = b.items[0].volumeInfo.description
                // TODO isbn 13だけとりたい
                it.identifier = b.items[0].volumeInfo.industryIdentifiers.first().identifier
                it.smallThumbnail = b.items[0].volumeInfo.imageLinks?.smallThumbnail
                it.thumbnail = b.items[0].volumeInfo.imageLinks?.thumbnail
                it.amount = b.items[0].saleInfo.listPrice?.amount
            }
            bookRepository.createBook(result)
        }
    }
}