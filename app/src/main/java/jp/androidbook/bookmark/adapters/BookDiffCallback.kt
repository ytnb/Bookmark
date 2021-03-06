package jp.androidbook.bookmark.adapters

import androidx.recyclerview.widget.DiffUtil
import jp.androidbook.bookmark.data.db.BookEntity

class BookDiffCallback : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem == newItem
    }
}
