package com.demo.demoapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
    val id: Int,
    val is_catering: Boolean,
    val name: String,
    val popular_items: List<PopularItem>,
    val subtitle: String
): Parcelable