package com.example.data.local

import androidx.room.*
import com.example.domain.entities.Movie

@Dao
interface ClassDao {
    @Insert
    suspend fun insertAppointment(appointment: Movie)
}

