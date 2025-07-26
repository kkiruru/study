import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {

    val a = intArrayOf(3, 8, 9, 7, 6)
    val k = 3
    val result = solution(a, k)
    println(result.joinToString(", ")) // 출력: 9, 7, 6, 3, 8

}

fun solution(A: IntArray, K: Int): IntArray {


    return A
}