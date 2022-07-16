package com.example.data.remote


import com.example.domain.RemoteDataSource
import com.example.domain.entities.MoviesProviders
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val theMovieDbApi: TheMovieDbApi,
) : RemoteDataSource {

    override suspend fun getProviders(): MoviesProviders {
        return theMovieDbApi.getProviders()
    }

}