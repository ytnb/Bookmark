package jp.androidbook.bookmark.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.androidbook.bookmark.BookSearchFragmentDirections
import jp.androidbook.bookmark.R
import jp.androidbook.bookmark.data.Items
import jp.androidbook.bookmark.databinding.SearchListItemBinding

class BookSearchAdapter :
    PagedListAdapter<Items, BookSearchAdapter.ItemsHolder>(ItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val binding: SearchListItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_list_item,
                parent,
                false
            )
        return ItemsHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            // TODO isbn 13だけ取りたい
            val isbn = item.volumeInfo.industryIdentifiers.first().identifier
            holder.bind(item, createOnClickListener(isbn))
        } else {
            // TODO ????
        }
    }

    private fun createOnClickListener(isbn: String): View.OnClickListener {
        return View.OnClickListener {
            val action =
                BookSearchFragmentDirections.actionBookSearchFragmentToBookSearchDetailFragment(isbn)
            it.findNavController().navigate(action)
        }
    }

    class ItemsHolder(private val binding: SearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items, clickListener: View.OnClickListener) {
            with(binding) {
                this.volumeInfo = item.volumeInfo
                this.clickListener = clickListener
                executePendingBindings()
            }
        }
    }
}
