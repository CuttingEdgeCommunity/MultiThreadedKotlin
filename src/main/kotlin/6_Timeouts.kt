import kotlinx.coroutines.*
import kotlin.random.Random

fun main() = runBlocking {
    val coroutine = async {
        withTimeout(500) {
            try {
                val time = Random.nextLong(1000)
                println("It will take me $time")
                delay(time)
                "Profile"
            } catch (e: TimeoutCancellationException) {
                println(e)
            }
        }
    }

    println("Result: ${coroutine.await()}")

}
