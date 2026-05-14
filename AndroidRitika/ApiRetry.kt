//Retry API 3 times if failed using coroutine. with exponential backoff

// The general formula for exponential backoff without jitter is:
//  (delay = base_delay *  2^attempt)

fun getApiData() = flow{
                    emit(callApi())
                    }.retry(retries = 3){cause ->
                        if(cause is IOException){
                            // Exponential backoff calculation (e.g., 1s, 2s, 4s)
                            val delayTime = 1000L * (2.0.pow(attempt.toDouble())).toLong()
                            delay(delayTime)
                            return@retry true // Retries the flow
                        }
                        false // Don't retry for other exceptions
                    }