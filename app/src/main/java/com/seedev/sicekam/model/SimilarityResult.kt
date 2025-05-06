package com.seedev.sicekam.model

import com.google.gson.annotations.SerializedName

data class SimilarityResult(
    @SerializedName("original_text1")
    val originalText1: String,
    @SerializedName("original_text2")
    val originalText2: String,
    @SerializedName("processed_text1")
    val processedText1: String,
    @SerializedName("processed_text2")
    val processedText2: String,
    val similarity: Double,
    @SerializedName("similarity_percent")
    val similarityPercent: String,
    @SerializedName("similarity_status")
    val similarityStatus: String,
    val timestamp: Long = System.currentTimeMillis()
)