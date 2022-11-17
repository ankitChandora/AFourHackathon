package com.example.afourhackathon.data

import com.example.afourhackathon.data.model.GiftsResponse

import com.example.afourhackathon.data.model.Post
import com.example.afourhackathon.data.network.ApiClient
import com.example.afourhackathon.data.network.ApiResult
import com.example.afourhackathon.data.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by ChandoraAnkit on 16/11/22
 */
class DataRepository {

    companion object{

        private var instance: DataRepository?  = null

        fun getInstance(): DataRepository{
            if (instance == null){
                instance = DataRepository()
            }
            return instance!!
        }
    }

    private val retrofitService by lazy { ApiClient.getClient().create(RetrofitService::class.java) }

    suspend fun fetchPosts(): ApiResult<List<Post>> {
        return try {
            withContext(Dispatchers.IO) {
                ApiResult.Success(retrofitService.fetchPosts())
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    suspend fun fetchGifts(): ApiResult<GiftsResponse> {
        return try {
            withContext(Dispatchers.IO) {
                ApiResult.Success(retrofitService.fetchGifts())
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}