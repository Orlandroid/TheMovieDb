package com.example.data.di

import com.example.data.Repositorio
import com.example.data.TheMovieDbAuth
import com.example.data.helpers.ApiInterceptor
import com.example.data.local.LocalDataSourceImpl
import com.example.data.remote.RemoteDataSourceImpl
import com.example.data.remote.TheMovieDbApi
import com.example.domain.LocalDataSource
import com.example.domain.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApi {

    private const val BASE_URL_V3 = "https://api.themoviedb.org/3/"
    private const val BASE_URL_V4 = "https://api.themoviedb.org/4/"
    private const val MOVIE_DB_V3 = "MovieDbV3"
    private const val MOVIE_DB_V4 = "MovieDbV4"
    private const val BEARER_INTERCEPTOR = "bearerInterceptor"
    private const val OK_HTTP_CLIENT_V3 = "OkHttpClientV3"
    private const val OK_HTTP_CLIENT_V4 = "OkHttpClientV4"

    @Singleton
    @Provides
    fun provideUnsafeOkHttpClientV3(interceptor: Interceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    /*
    @Singleton
    @Provides
    @Named(OK_HTTP_CLIENT_V4)
    fun provideUnsafeOkHttpClientV4(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(ApiInterceptor())
            .retryOnConnectionFailure(true)
            .build()
    }*/

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader("Authorization", TheMovieDbAuth.BEARER)
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }


    @Singleton
    @Provides
    fun provideTheMovieDbApiServiceV3(retrofit: Retrofit): TheMovieDbApi =
        retrofit.create(TheMovieDbApi::class.java)

    /*
    @Singleton
    @Provides
    fun provideTheMovieDbApiServiceV4(@Named(MOVIE_DB_V4) retrofit: Retrofit): TheMovieDbApi =
        retrofit.create(TheMovieDbApi::class.java)*/

    @Singleton
    @Provides
    fun provideRetrofitMovieDbV3(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_V3)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    /*
    @Singleton
    @Provides
    @Named(MOVIE_DB_V4)
    fun provideRetrofitMovieDbV4(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_V4)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()*/

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSourceImpl,
        remoteDataSource: RemoteDataSourceImpl
    ): Repositorio = Repositorio(localDataSource, remoteDataSource)


}