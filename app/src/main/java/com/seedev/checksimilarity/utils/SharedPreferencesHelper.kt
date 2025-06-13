package com.seedev.checksimilarity.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seedev.checksimilarity.model.SimilarityResult

object SharedPrefHelper {
    private const val PREF_NAME = "similarity_history"
    private const val KEY_HISTORY = "history"

    fun saveToHistory(context: Context, newResult: SimilarityResult) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        val gson = Gson()
        val existingJson = sharedPref.getString(KEY_HISTORY, "[]")
        val type = object : TypeToken<MutableList<SimilarityResult>>() {}.type

        val historyList: MutableList<SimilarityResult> = gson.fromJson(existingJson, type)
        historyList.add(0, newResult) // tambahkan ke atas

        val updatedJson = gson.toJson(historyList)
        editor.putString(KEY_HISTORY, updatedJson)
        editor.apply()
    }


    fun getHistory(context: Context): List<SimilarityResult> {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(KEY_HISTORY, "[]")
        val type = object : TypeToken<List<SimilarityResult>>() {}.type
        return Gson().fromJson(json, type)
    }

    fun removeFromHistory(context: Context, itemToRemove: SimilarityResult) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val type = object : TypeToken<MutableList<SimilarityResult>>() {}.type
        val json = sharedPref.getString(KEY_HISTORY, "[]")
        val historyList: MutableList<SimilarityResult> = gson.fromJson(json, type)

        historyList.remove(itemToRemove)

        val updatedJson = gson.toJson(historyList)
        sharedPref.edit().putString(KEY_HISTORY, updatedJson).apply()
    }


    fun clearHistory(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_HISTORY).apply()
    }
}
