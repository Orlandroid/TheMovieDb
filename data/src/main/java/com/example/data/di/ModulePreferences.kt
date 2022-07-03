package com.example.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModulePreferences {

    private const val SHARE_PREFERENCES = "TheMovieDb"

    @Singleton
    @Provides
    fun provideSharePreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARE_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun providePreferencesManager(sharedPreferences: SharedPreferences): PreferencesManager {
        return PreferencesManager(sharedPreferences)
    }

}