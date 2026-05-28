package com.example.dailysysdesprac

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class PostRepository @Inject constructor(private val apiService:ApiService) {
    fun fetchJobs(): Flow<List<JobPost>> = flow{
        val response = apiService.getJobs()
        emit(response)
    }
}