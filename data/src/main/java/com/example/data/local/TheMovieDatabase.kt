package com.example.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entities.Movie

@Database( entities = [Movie::class],version = 2, exportSchema = false)

abstract class TheMovieDatabase : RoomDatabase() {

    abstract fun appointmentDao(): ClassDao
}