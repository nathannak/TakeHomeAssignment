package com.demo.demoapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Status(
    val asap_available: Boolean,
    val asap_minutes_range: List<Int>,
    val pickup_available: Boolean,
    val scheduled_available: Boolean,
    val unavailable_reason: @RawValue Any
) : Parcelable