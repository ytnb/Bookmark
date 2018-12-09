package jp.androidbook.bookmark.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import jp.androidbook.bookmark.data.Book
import jp.androidbook.bookmark.data.api.GoogleBookClients

class BookSearchDetailViewModel(isbn: String): ViewModel() {

    val book: LiveData<Book> = GoogleBookClients.getBookDetail(isbn)

}