import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val a = intArrayOf(3, 8, 9, 7, 6)
    val result = solution(a)
    println(a.joinToString(", "))
    println(result)
}

private fun solution(A: IntArray): Int {
    return 0
}