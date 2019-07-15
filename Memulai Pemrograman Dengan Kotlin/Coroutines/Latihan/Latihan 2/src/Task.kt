import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        delay(6000)
        val result = 100 * 100
        println("Result: $result")
    }

    delay(4000)
    job.cancel()
    println("Membatalkan Job...")
    if (job.isCancelled){
        println("Job dibatalkan")
    }
}