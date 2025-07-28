import kotlinx.coroutines.runBlocking


fun main(): kotlin.Unit = runBlocking<Unit> {
    val n = 36
    val result = solution(n)
}

private fun solution(N: Int): Int {
    println(N)

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

    println("\n${count}")

    return count
}