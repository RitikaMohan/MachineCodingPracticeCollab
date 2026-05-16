
//Thread Switching

//Fetch data on IO thread and update UI on Main thread.

/*The withContext(Dispatchers.IO) { ... } block immediately shifts execution of api.fetchData() to 
a shared thread pool optimized for Input/Output operations (e.g., network requests, database reads).*/
fun threadSwitching(){
    viewModelScope.launch{
        // on background thread
        val result = withContext(Dispatcher.IO){
            api.fetchData()
        }
        // Execution automatically resumes on the Main thread here
        uiState.text = result
    }
}