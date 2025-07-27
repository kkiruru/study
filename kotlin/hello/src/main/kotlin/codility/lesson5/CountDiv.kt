package codility.lesson5

import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val a = 2
    val b = 3
    val k = 4
    val result = solution(a, b, k)
    println("$a, $b, $k")
    println(result)
}

private fun solution(A: Int, B: Int, K: Int): Int {
    return 0
}