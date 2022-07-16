package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.TheMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDb {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): TheMovieDatabase {
        return Room.databaseBuilder(
            context,
            TheMovieDatabase::class.java,
            "THE_MOVIE_DB"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppointmentDao(db: TheMovieDatabase) = db.appointmentDao()

}