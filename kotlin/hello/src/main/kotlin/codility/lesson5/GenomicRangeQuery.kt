package codility.lesson5

import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val s = "CAGCCTA"
    val p = intArrayOf(2, 5, 0)
    val q = intArrayOf(4, 5, 6)
    val result = solution(s, p, q)
    println("$s, $p, $q")
    println(result)
}

private fun solution(S: String, P: IntArray, Q: IntArray): IntArray {
    return P
}