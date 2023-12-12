package com.capstone.cuacatani.di

import android.content.Context
import com.capstone.cuacatani.data.UserRepository
import com.capstone.cuacatani.data.pref.UserPreference
import com.capstone.cuacatani.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}