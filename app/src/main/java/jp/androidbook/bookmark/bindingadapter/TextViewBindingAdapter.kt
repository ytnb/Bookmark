package jp.androidbook.bookmark.bindingadapter

import android.databinding.BindingAdapter
import android.widget.TextView

@BindingAdapter("text")
fun TextView.setListString(list: List<String>?) {
    this.text = list?.run {
        // TODO authorなしの場合、別文字いれる？
        this.joinToString()
    }
}