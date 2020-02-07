package jp.androidbook.bookmark.adapters

import androidx.recyclerview.widget.DiffUtil
import jp.androidbook.bookmark.data.Items

class ItemsDiffCallback : DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        // TODO 比較部分の検討要
        return oldItem.volumeInfo.title == newItem.volumeInfo.title
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }
}