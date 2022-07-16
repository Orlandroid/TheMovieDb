package com.example.data.remote

import com.example.domain.entities.MoviesProviders
import retrofit2.http.GET
import com.example.domain.state.Result

interface TheMovieDbApi {

    @GET("watch/providers/movie")
    fun getProviders(): MoviesProviders

    @GET("movie/popular")
    fun getPopulars(): Result<String>

    @GET("movie/upcoming")
    fun getUpComming(): Result<String>

    @GET("movie/top_rated")
    fun getTopRated(): Result<String>

    @GET("movie/latest")
    fun getLasted(): Result<String>

    @GET("/movie/{movie_id}")
    fun getDetailMovie(): Result<String>

    @GET("/genre/movie/list")
    fun getGeneres(): Result<String>

    @GET("list/{id}")
    fun getAllMoviesByUserId(): Result<String>


}