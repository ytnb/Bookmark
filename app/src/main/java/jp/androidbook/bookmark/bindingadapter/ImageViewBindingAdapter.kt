package jp.androidbook.bookmark.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import jp.androidbook.bookmark.GlideApp
import jp.androidbook.bookmark.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        GlideApp.with(this.context).load(url).apply(RequestOptions().fitCenter()).into(this)
    } else {
        GlideApp.with(this.context).clear(this)
        this.setImageDrawable(null)
        this.setImageResource(R.drawable.m_e_others_471)
    }
}