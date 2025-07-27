package codility.lesson6

import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val a = intArrayOf(1, 5, 2, 1, 4, 0)
    val result = solution(a)
    println(result)
}

private fun solution(A: IntArray): Int {
    println(A.contentToString())

    return 0
}