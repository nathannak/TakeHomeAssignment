package com.demo.demoapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeliveryFeeMonetaryFields(
    val currency: String,
    val decimal_places: Int,
    val display_string: String,
    val unit_amount: Int
) : Parcelable