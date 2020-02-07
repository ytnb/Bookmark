package jp.androidbook.bookmark.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("text")
fun TextView.setListString(list: List<String>?) {
    this.text = list?.run {
        // TODO authorなしの場合、別文字いれる？
        this.joinToString()
    }
}