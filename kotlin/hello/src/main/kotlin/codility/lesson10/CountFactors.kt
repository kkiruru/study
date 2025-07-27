import kotlinx.coroutines.runBlocking


fun main(): kotlin.Unit = runBlocking<Unit> {
    val n = 36
    println(n)

    val result = solution(n)

    println("\n$result")
}

private fun solution(N: Int): Int {
    if (N == 1) {
        return 1
    }

    var i = 1L
    var count = 0
    while(i * i < N) {
        if (N % i == 0L) {
            print("$i ${N/i} ")
            count = count + 2
        }
        i++
    }

    if ((i * i).toInt() == N) {
        print("$i ")
        count++
    }


    return count
}