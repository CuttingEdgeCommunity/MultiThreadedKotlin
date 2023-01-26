import kotlinx.coroutines.*

fun main() = runBlocking {
    val cancellable = getCancellable()
    val notCancellable = getNotCancellable()

    delay(100)

    // We cancel both coroutines
    cancellable.cancel()
    notCancellable.cancel()

    // By invoking join(), we can wait for the execution of the coroutine to complete.
    cancellable.join()
    notCancellable.join()
}

private fun CoroutineScope.getNotCancellable() = launch {
    for (i in 1..10_000) {
        if (i % 100 == 0) {
            println("Not cancellable $i")
        }
    }
}

private fun CoroutineScope.getCancellable() = launch {
    try {
        for (i in 1..1000) {
            if (i % 100 == 0) {
                println("Cancellable: $i")
                yield()
            }
        }
    } catch (e: CancellationException) {
        println(e.printStackTrace())
    }
}
