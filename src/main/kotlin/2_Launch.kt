import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

fun main() {
    runThreads()
    runCoroutines()
}

fun runThreads() {
    val counter = AtomicInteger()
    val start = System.currentTimeMillis()

    for (i in 0..10_000) {
        thread {
            counter.incrementAndGet()
            Thread.sleep(100)
        }
    }

    println("Threads: $counter in ${System.currentTimeMillis() - start}ms")
}

private fun runCoroutines() {
    val latch = CountDownLatch(10_000)
    val counter = AtomicInteger()
    val start = System.currentTimeMillis()

    for (i in 1..10_000) {
        GlobalScope.launch {
            counter.incrementAndGet()
            delay(100)
            latch.countDown()
        }
    }

    latch.await(10, TimeUnit.SECONDS)

    println("Coroutines: ${counter.get()} in ${System.currentTimeMillis() - start}ms")
}