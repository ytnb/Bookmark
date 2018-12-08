package jp.androidbook.bookmark.viewmodels

import android.arch.lifecycle.*
import jp.androidbook.bookmark.data.Book
import jp.androidbook.bookmark.data.api.GoogleBookClients

class BookSearchViewModel : ViewModel() {

    private val title: MutableLiveData<String> = MutableLiveData()
    val bookSearchResult: MediatorLiveData<Book> = MediatorLiveData()

    init {
        title.value = ""
        val list = Transformations.switchMap(title) { title ->
            Transformations.map(bookSearch(title)) { book ->
                // ISBN番号があるものだけデータとして渡す
                val items = book.items.filter {
                    !it.volumeInfo.industryIdentifiers.isNullOrEmpty()
                }
                Book(book.totalItems, items)
            }
        }
        bookSearchResult.addSource(list, bookSearchResult::setValue)
    }

    fun setTitle(title: String?) {
        this.title.value = title ?: ""
    }

    private fun bookSearch(title: String): LiveData<Book> {
        // TODO titleをもらい[intitle:]を追加してapi問い合わせする
        return GoogleBookClients.getBookFromTitle("intitle:Android")
    }
}