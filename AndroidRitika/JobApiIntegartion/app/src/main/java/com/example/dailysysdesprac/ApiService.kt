package com.example.dailysysdesprac

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getJobs(): Flow<List<JobPost>>
}