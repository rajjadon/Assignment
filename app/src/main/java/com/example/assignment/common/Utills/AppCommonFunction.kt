package com.example.assignment.common.Utills

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.assignment.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView)
            .load(it)
            .into(imageView)
    }
}

fun setDrawableIconOnLeft(textView: TextView, iconId: Int) {
    textView.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0)
}

fun showImageInImageView(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .apply(getCircleCropRequestOption())
        .into(imageView)
}

fun getCircleCropRequestOption(): RequestOptions {
    return RequestOptions()
        .transform(CircleCrop())
        .placeholder(R.drawable.ic_camera_ellipse_bg)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .priority(Priority.HIGH)
}