package jp.androidbook.bookmark.bindingadapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context).load(url).apply(RequestOptions().centerCrop()).into(this)
    } else{
        Glide.with(this.context).clear(this)
        this.setImageDrawable(null)
    }
}