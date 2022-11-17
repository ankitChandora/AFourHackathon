package com.example.afourhackathon.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ChandoraAnkit on 16/11/22
 */


object ApiClient {

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        val client = OkHttpClient.Builder().build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!
    }
}