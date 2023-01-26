import kotlinx.coroutines.*
import java.util.*

fun main() {
    firstTryAsync()
    secondTryAsync()
}

fun firstTryAsync() {
    println(fastUuidAsync())
}

fun secondTryAsync() {
    runBlocking {
        val job: Deferred<UUID> = fastUuidAsync()
        println(job.await())
    }
}

fun fastUuidAsync() = GlobalScope.async {
    UUID.randomUUID()
}
