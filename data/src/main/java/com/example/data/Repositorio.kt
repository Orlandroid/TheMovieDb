package com.example.data

import com.example.data.local.LocalDataSourceImpl
import com.example.data.remote.RemoteDataSourceImpl
import com.example.domain.entities.MoviesProviders
import javax.inject.Inject

class Repositorio @Inject constructor(
    private val localDataSource: LocalDataSourceImpl,
    private val remoteDataSource: RemoteDataSourceImpl
) {
    suspend fun getProviders(): MoviesProviders {
        return remoteDataSource.getProviders()
    }
}