package codility

import kotlinx.coroutines.runBlocking


fun main(): kotlin.Unit = runBlocking<Unit> {

    // 예시 1: 문제에서 제공한 예시
    val inner1 = 1
    val outer1 = 3
    val points_x1 = intArrayOf(0, 1, 2, -2, 3)
    val points_y1 = intArrayOf(0, 1, 4, 1, 0)
    
    val result1 = solution(inner1, outer1, points_x1, points_y1)
    println("예시 1 결과: $result1 (기대값: 2)")
    
    // 예시 2: 모든 점이 링 안에 있는 경우
    val inner2 = 0
    val outer2 = 5
    val points_x2 = intArrayOf(1, 2, -1, -2)
    val points_y2 = intArrayOf(1, 2, -1, -2)
    
    val result2 = solution(inner2, outer2, points_x2, points_y2)
    println("예시 2 결과: $result2 (기대값: 4)")
    
    // 예시 3: 링이 없는 경우 (inner >= outer)
    val inner3 = 3
    val outer3 = 1
    val points_x3 = intArrayOf(1, 2, 3)
    val points_y3 = intArrayOf(1, 2, 3)
    
    val result3 = solution(inner3, outer3, points_x3, points_y3)
    println("예시 3 결과: $result3 (기대값: 0)")
    
    // 예시 4: 경계에 있는 점들
    val inner4 = 2
    val outer4 = 4
    val points_x4 = intArrayOf(2, 4, 0, -2, -4)
    val points_y4 = intArrayOf(0, 0, 2, 0, 0)
    
    val result4 = solution(inner4, outer4, points_x4, points_y4)
    println("예시 4 결과: $result4 (기대값: 0)")
    
    // 예시 5: 빈 배열
    val inner5 = 1
    val outer5 = 3
    val points_x5 = intArrayOf()
    val points_y5 = intArrayOf()
    
    val result5 = solution(inner5, outer5, points_x5, points_y5)
    println("예시 5 결과: $result5 (기대값: 0)")

}




private fun solution(inner: Int, outer: Int, points_x: IntArray, points_y: IntArray): Int {

    println("inner ${inner}")
    println("outer ${outer}")
    println("points_x ${points_x.contentToString()}")
    println("points_y ${points_y.contentToString()}")

    var count = 0


    for( i in 0 until points_x.size) {

        val x = points_x[i]
        val y = points_y[i]

        val point = Math.sqrt((x*x).toDouble() + (y*y).toDouble())

        println("[$x, $y], $point")

        if (inner < point && point < outer) {
            count++
        }
    }
    println("${count}")
    return count
}
