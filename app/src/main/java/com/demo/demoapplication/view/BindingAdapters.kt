package com.demo.demoapplication.view

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.demo.demoapplication.R.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
Binding adapter to move logic out of View classes; also using these binds, there is no need to pass
ViewModel into Recyclerview
Writeen by NN
 */

@BindingAdapter("imageUrl")
fun setImageUrl(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .placeholder(mipmap.loading_image_foreground)
            .error(mipmap.fail_placeholder_foreground)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .into(imgView)
    }
}

@BindingAdapter("setCircularImage")
fun de.hdodenhof.circleimageview.CircleImageView.setCircularImage(imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imgUri)
            .placeholder(mipmap.loading_image_foreground)
            .error(mipmap.food_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .into(this)
    }
}

@BindingAdapter("setDescToTextView")
fun TextView.setDescToTextView(str: String?): String {
    var res = ""
    val typesArray = str?.split(",")

    if(typesArray?.size==1) {
        res = typesArray.get(0)
    }
    else if(typesArray?.size!! > 1){
        res = typesArray.get(0).plus(",").plus(typesArray.get(1))
    }

    this.text = res
    return res
}

@BindingAdapter("minutesToClose")
fun TextView.minutesToClose(time: String?): String {

    var res = ""

    val originalTimeArr = time?.split("|")
    val open = LocalDateTime.parse(
        originalTimeArr?.get(0),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    )
    val close = LocalDateTime.parse(
        originalTimeArr?.get(1),
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    )

    val minutesToClose = Duration.between(
        open,
        close
    ).toMinutes()

    //logic for time remaining to close
    if (open.isEqual(close) || open.isAfter(close)) {
        res = "closed"
        this.text = res
    } else if (minutesToClose / 60 >= 24) {
        res = "open"
        this.text = res
    } else if (minutesToClose < 60) {
        res = minutesToClose.toString().plus(" min")
        this.text = res
    } else {
        res =
            ((minutesToClose / 60).toString().plus(" h, ").plus(minutesToClose % 60).plus(" min"))
        this.text = res
    }

    return res
}
