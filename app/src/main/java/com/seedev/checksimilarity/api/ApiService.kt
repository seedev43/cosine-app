package com.seedev.checksimilarity.api

import com.seedev.checksimilarity.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("similarity")
    suspend fun checkSimilarity(
        @Query("text1") text1: String,
        @Query("text2") text2: String,
    ): ApiResponse
}