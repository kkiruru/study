import kotlinx.coroutines.runBlocking
import kotlin.math.abs


fun main() = runBlocking<Unit> {

    val a = intArrayOf(3, 3, 3, 4, 5)
    val result = solution(a)
    println(a.joinToString(", "))
    println(result)

    val resultB = solutionB(a)
    println(resultB)
}


private fun solution(A: IntArray): Int {
    if (A.isEmpty())
        return 0

    val maxSum = A.sumOf { kotlin.math.abs(it) }
    val minSum = -maxSum

    val dp = Array(A.size + 1) { BooleanArray(2 * maxSum + 1) { false } }

    dp[0][maxSum] = true

    for (i in A.indices) {
        for (sum in minSum..maxSum) {
            val currentSum = sum + maxSum // 배열 인덱스로 변환
            if (dp[i][currentSum]) {
                // +1을 곱한 경우
                val nextSum1 = currentSum + A[i]
                if (nextSum1 in 0..2 * maxSum) {
                    dp[i + 1][nextSum1] = true
                }

                // -1을 곱한 경우
                val nextSum2 = currentSum - A[i]
                if (nextSum2 in 0..2 * maxSum) {
                    dp[i + 1][nextSum2] = true
                }
            }
        }
    }

    var minAbsSum = Int.MAX_VALUE
    for (sum in minSum..maxSum) {
        val currentSum = sum + maxSum
        if (dp[A.size][currentSum]) {
            minAbsSum = minOf(minAbsSum, kotlin.math.abs(sum))
        }
    }

    return minAbsSum
}


fun solutionB(A: IntArray): Int {
    val N = A.size
    if (N == 0) {
        return 0
    }

    val absA = A.map { abs(it) }.filter { it > 0 } // 0은 결과에 영향 안 주므로 제외 가능
    val S = absA.sum()
    val target = S / 2

    // dp[i]는 합계 i를 만들 수 있으면 true
    val dp = BooleanArray(target + 1) { false }
    dp[0] = true

    print("absA ${absA}\n")

    for (num in absA) {
        print("_ num ${num}\n")
        for (s in target downTo num) {
            print("__s ${s}\n")
            if (dp[s - num]) {
                dp[s] = true
            }
        }
    }

    var pClosestToTarget = 0
    for (i in target downTo 0) {
        if (dp[i]) {
            pClosestToTarget = i
            break
        }
    }
    return S - 2 * pClosestToTarget
}
