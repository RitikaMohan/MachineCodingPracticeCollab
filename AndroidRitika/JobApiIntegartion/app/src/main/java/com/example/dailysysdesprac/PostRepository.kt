package com.example.dailysysdesprac

class PostRepository(private val apiService:ApiService) {
    suspend fun fetchJobs() = apiService.getJobs()
}