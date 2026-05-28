package com.example.dailysysdesprac

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun JobsScreen(navController: NavController,
               viewModel: JobViewModel = hiltViewModel()
){

    LaunchedEffect(Unit) {
        viewModel.loadJobs()
    }

    val jobsList by viewModel.jobs.collectAsState()
    LazyColumn{
        items(jobsList){job->
            Text(text = job.description)
        }
    }
}