package com.demo.demoapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularItem(
    val description: String,
    val id: Int,
    val img_url: String,
    val name: String,
    val price: Int
): Parcelable