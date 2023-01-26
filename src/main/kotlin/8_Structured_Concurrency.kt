import kotlinx.coroutines.*
import java.util.*

fun main() = runBlocking {
    val parent = launch(Dispatchers.Default) {
        val children = List(10) { childId ->
            supervisorScope {
                launch {
                    for (i in 1..1_000_000) {
                        UUID.randomUUID() // do something

                        if (i % 100_000 == 0) {
                            println("$childId - $i")
                            yield()
                        }

                        if (childId == 3 && i == 300_000) {
                            throw RuntimeException("Something bad happened")
                        }
                    }
                }
            }
        }
    }
}
