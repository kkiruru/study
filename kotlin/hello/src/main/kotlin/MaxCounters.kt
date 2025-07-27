import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val a = intArrayOf(3, 8, 9, 7, 6)
    val n = 5
    val result = solution(n, a)
    println(a.joinToString(", "))
    println(result.joinToString(", "))
}

private fun solution(N: Int, A: IntArray): IntArray {
    return A
}