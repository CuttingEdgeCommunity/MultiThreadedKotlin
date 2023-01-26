import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread


fun main() {
    startingThreads()
//    threadRace()
//    synchronisingThreads()
}

fun startingThreads() {
    repeat(2) { t ->
        thread {
            for (i in 1..100) {
                println("Thread$t: $i")
            }
        }
    }
}

fun threadRace() {
    var counter = 0
    repeat(100) {
        thread {
            repeat(1000) {
                counter++
            }
        }
    }
    println("Counter: $counter")
}

fun synchronisingThreads() {
    var counter = 0
    val latch = CountDownLatch(100_000)
    repeat(100) {
        thread {
            repeat(1000) {
                synchronized(latch) {
                    counter++
                    latch.countDown()
                }
            }
        }
    }
    latch.await()
    println("Counter: $counter")
}
