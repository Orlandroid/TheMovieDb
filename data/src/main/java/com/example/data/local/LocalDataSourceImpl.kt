package com.example.data.local

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSourceImpl @Inject constructor(
    private val dao: ClassDao
){

    suspend fun doSomething(){

    }
}