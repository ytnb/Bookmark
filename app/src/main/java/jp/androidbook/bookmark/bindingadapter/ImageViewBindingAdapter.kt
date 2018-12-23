package jp.androidbook.bookmark.bindingadapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.androidbook.bookmark.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context).load(url).apply(RequestOptions().fitCenter()).into(this)
    } else {
        Glide.with(this.context).clear(this)
        this.setImageDrawable(null)
        this.setImageResource(R.drawable.m_e_others_471)
    }
}