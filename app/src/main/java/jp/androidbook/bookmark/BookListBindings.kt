package jp.androidbook.bookmark

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.androidbook.bookmark.adapters.BookListAdapter
import jp.androidbook.bookmark.data.db.BookEntity

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<BookEntity>?) {
    items?.let {
        (listView.adapter as BookListAdapter).submitList(items)
    }
}