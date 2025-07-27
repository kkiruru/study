package codility.lesson5

import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {

    val a = intArrayOf(2, 5, 0)

    println("${a.contentToString()}")

    val result = solution(a)
    println(result)

    println(solution(intArrayOf(4, 2, 2, 5, 1, 5, 8)))
    println(solution(intArrayOf(10, 2, 8, 1, 9, 3, 7)))
    println(solution(intArrayOf(-3, -5, -8, -4, -10)))
}

private fun solution(A: IntArray): Int {

    var foo = 0

    var minAvg = Double.MAX_VALUE
    var minStartIndex = 0

    for(i in 0 until A.size - 1) {
        var avg = (A[i] + A[i+1]) / 2.0
        if (avg < minAvg) {
            minAvg = avg
            println("> avg ${avg} : ${A[i]}, ${A[i+1]}")
        }
        minStartIndex = i
    }

    for(i in 0 until A.size - 2) {
        var avg = (A[i] + A[i+1] + A[i+2]) / 3.0
        if (avg < minAvg) {
            minAvg = avg
            println("> avg ${avg} : ${A[i]}, ${A[i + 1]}, ${A[i + 2]}\n")
        }
        minStartIndex = i
    }


    return minStartIndex
}