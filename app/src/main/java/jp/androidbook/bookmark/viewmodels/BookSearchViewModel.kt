package jp.androidbook.bookmark.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import jp.androidbook.bookmark.data.Book
import jp.androidbook.bookmark.data.api.GoogleBookClients

class BookSearchViewModel : ViewModel() {

    fun bookSearchResult(): LiveData<Book> {
        // TODO titleをもらい[intitle:]を追加してapi問い合わせする
        return GoogleBookClients.getBookFromTitle("intitle:Android")
    }
}