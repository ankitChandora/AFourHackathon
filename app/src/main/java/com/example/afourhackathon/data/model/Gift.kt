package com.example.afourhackathon.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ChandoraAnkit on 17/11/22
 */


data class GiftsResponse(
    @SerializedName("coins") val coins: Int?,
    @SerializedName("gifts") val gifts: List<Gift> = emptyList(),
)

data class Gift(
    @SerializedName("id") val assetId: String?,
    @SerializedName("price") val price: Float?,
)