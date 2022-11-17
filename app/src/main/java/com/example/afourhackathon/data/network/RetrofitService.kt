package com.example.afourhackathon.data.network

import com.example.afourhackathon.data.model.GiftsResponse
import com.example.afourhackathon.data.model.Post
import retrofit2.http.GET

/**
 * Created by ChandoraAnkit on 16/11/22
 */
interface RetrofitService {

    @GET("https://demo3796422.mockable.io/posts")
    suspend fun fetchPosts(): List<Post>

    @GET("https://demo3796422.mockable.io/gifts")
    suspend fun fetchGifts(): GiftsResponse
}