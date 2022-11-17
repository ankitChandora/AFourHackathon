package com.example.afourhackathon.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by ChandoraAnkit on 16/11/22
 */

data class Post(
    @SerializedName("type") val type: String?,
    @SerializedName("user") val user: UserDetails?,
    @SerializedName("caption") val caption: String,
    @SerializedName("postUrl") val postUrl: String?,
    @SerializedName("views") val views: String?,
    @SerializedName("comments") val comments: String?,
    @SerializedName("likes") val likes: String,
    @SerializedName("shares") val shares: String?,
    @SerializedName("gift") val gifts: String?
)

data class UserDetails(
    @SerializedName("profileImg") val profileImg: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("bio") val bio: String?,
)