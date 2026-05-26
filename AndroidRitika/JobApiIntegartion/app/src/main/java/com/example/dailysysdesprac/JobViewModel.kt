package com.example.dailysysdesprac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class JobViewModel(private val repo: PostRepository) : ViewModel(){
    private val _jobs = MutableStateFlow<List<JobPost>>(emptyList())
    var jobs: StateFlow<List<JobPost>> = _jobs.asStateFlow()

    init{
        loadJobs()
    }

    fun loadJobs(){
        viewModelScope.launch{
            repo.fetchJobs().collect{jobList ->
                _jobs.value = jobList
            }
        }
    }
}