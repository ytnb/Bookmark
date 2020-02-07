package jp.androidbook.bookmark.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import jp.androidbook.bookmark.data.api.BookApiRepository

class BookSearchViewModel(private val repository: BookApiRepository) : ViewModel() {
    private val title: MutableLiveData<String> = MutableLiveData()

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    val bookSearchResult = Transformations.switchMap(title) { title ->
        repository.getPagedList("intitle:$title")
    }

    val isNotEmpty: LiveData<Boolean>
        get() = Transformations.map(title) { it.isNotEmpty() }

    init {
        isLoading.value = false
    }

    fun setTitle(title: String?) {
        this.title.value = title ?: ""
        this.isLoading.value = true
    }

    fun finishedLoading() {
        isLoading.value = false
    }

}