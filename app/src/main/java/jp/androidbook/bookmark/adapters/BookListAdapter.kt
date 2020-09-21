package jp.androidbook.bookmark.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.androidbook.bookmark.data.db.BookEntity
import jp.androidbook.bookmark.databinding.BookListItemBinding
import jp.androidbook.bookmark.viewmodels.BookListViewModel

class BookListAdapter(private val viewModel: BookListViewModel) :
    ListAdapter<BookEntity, BookListAdapter.BookHolder>(BookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BookHolder, postion: Int) {
        val book = getItem(postion)
        holder.bind(viewModel, book)
    }

    class BookHolder private constructor(val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: BookListViewModel, book: BookEntity) {
            with(binding) {
                this.viewModel = viewModel
                this.book = book
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): BookHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = BookListItemBinding.inflate(layoutInflater, parent, false)
                return BookHolder(binding)
            }
        }
    }
}
