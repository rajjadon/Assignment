package com.example.assignment.common.utills

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.assignment.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String?) {

    Timber.e(imageUrl)
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
        .placeholder(R.drawable.ic_camera_ellipse_bg)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .priority(Priority.HIGH)
}

fun getAppDateFromServerDate(
    date: String,
    appCalendarDateFormat: SimpleDateFormat,
    appServerDateFormat: SimpleDateFormat
): String =
    run {
        val calendar = Calendar.getInstance()

        try {
            appServerDateFormat.parse(date)?.let { calendar.time = it }
        } catch (e: Exception) {
            Timber.e(e)
        }
        appCalendarDateFormat.format(calendar.timeInMillis)
    }