package com.example.dailysysdesprac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class JobViewModel @Inject constructor(private val repo: PostRepository) : ViewModel(){
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