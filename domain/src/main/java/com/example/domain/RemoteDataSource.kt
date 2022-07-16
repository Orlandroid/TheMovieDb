package com.example.domain

import com.example.domain.entities.MoviesProviders

interface RemoteDataSource {
    suspend fun getProviders(): MoviesProviders
}