package com.demo.demoapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MerchantPromotion(
    val category_new_store_customers_only: Boolean,
    val daypart_constraints: @RawValue List<Any>,
    val delivery_fee: Int,
    val delivery_fee_monetary_fields: DeliveryFeeMonetaryFieldsX,
    val id: Int,
    val minimum_subtotal: Int,
    val minimum_subtotal_monetary_fields: MinimumSubtotalMonetaryFields
): Parcelable