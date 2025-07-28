package codility.lesson10

import kotlinx.coroutines.runBlocking

fun main(): kotlin.Unit = runBlocking<Unit> {
    val a = intArrayOf(1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2)

    val result = solution(a)
    println("result ${result}")

    val testCases = listOf(
        intArrayOf(1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2), // 예제: 결과 3
        intArrayOf(1, 2, 3, 4, 5, 6), // 피크 없음: 결과 0
        intArrayOf(1, 3, 2), // 피크 1개: 결과 1
        intArrayOf(1, 3, 2, 4, 1), // 피크 2개: 결과 2
        intArrayOf(1, 3, 2, 4, 1, 5, 2), // 피크 3개: 결과 2
        intArrayOf(1, 3, 2, 4, 1, 5, 2, 6, 1), // 피크 4개: 결과 3
        intArrayOf(1, 2, 1, 2, 1, 2, 1), // 피크 3개: 결과 2
        intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1), // 피크 4개: 결과 2
        intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1), // 피크 5개: 결과 3
        intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1), // 피크 6개: 결과 3
    )
    testCases.forEach {
        val result = solution(it)
        println("result ${result}")
    }

}

private fun solution(A: IntArray): Int {
    println(A.contentToString())

    if (A.size < 3)
        return 0

    var count = 0

    val peaks = mutableListOf<Int>()

    for (i in 1 until A.size - 1) {
        if (A[i -1] < A[i]  && A[i] > A[i+1]) {
            peaks.add(i)
        }
    }

    if (peaks.isEmpty())
        return 0

    println(peaks)

    var a = 0

    for (flags in peaks.size downTo 1) {
        var count = 0
        var find = -flags
        for (peak in peaks) {
            if (find + flags <= peak) {
                find = peak
                count ++
            }
            if (count == flags)
                break
        }

        if (count == flags)
            return flags

    }


    println("\n${peaks.size}")

    return 0
}