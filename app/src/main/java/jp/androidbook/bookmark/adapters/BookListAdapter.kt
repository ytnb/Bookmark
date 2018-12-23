package jp.androidbook.bookmark.adapters

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import jp.androidbook.bookmark.BookListFragmentDirections
import jp.androidbook.bookmark.R
import jp.androidbook.bookmark.data.db.BookEntity
import jp.androidbook.bookmark.databinding.BookListItemBinding

class BookListAdapter : ListAdapter<BookEntity, BookListAdapter.BookHolder>(BookDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val binding: BookListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.book_list_item, parent, false)
        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, postion: Int) {
        val book = getItem(postion)
        holder.bind(book, createOnClickListener(book.id))
    }

    private fun createOnClickListener(id: Int): View.OnClickListener {
        return View.OnClickListener {
            val action = BookListFragmentDirections.actionBookListFragmentToBookListDetailFragment(id)
            it.findNavController().navigate(action)
        }
    }

    class BookHolder(private val binding: BookListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookEntity, clickListener: View.OnClickListener) {
            with(binding) {
                binding.book = book
                binding.clickListener = clickListener
                executePendingBindings()
            }
        }
    }
}
