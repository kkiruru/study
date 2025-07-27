import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertArrayEquals


fun main() = runBlocking<Unit> {

    val a = intArrayOf(3, 8, 9, 7, 6)
    val k = 3
    val result = solution(a, k)
    println(a.joinToString(", ")) // 출력: 9, 7, 6, 3, 8
    println(result.joinToString(", ")) // 출력: 9, 7, 6, 3, 8
}

private fun solution(A: IntArray, K: Int): IntArray {

    if (A.isEmpty())
        return A

    val rotation = K % A.size
    if(rotation == 0)
        return A

    val result = IntArray(A.size)

    for (i in A.indices) {
        result[(i + rotation) % A.size] = A[i]
    }
    return result
}


class CyclicRotationTest {

    @org.junit.Test
    fun `빈 배열 테스트`() {
        val a = intArrayOf()
        val k = 3
        val expected = intArrayOf()
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `K가 0인 경우 테스트`() {
        val a = intArrayOf(3, 8, 9, 7, 6)
        val k = 0
        val expected = intArrayOf(3, 8, 9, 7, 6)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `기본 회전 테스트`() {
        val a = intArrayOf(3, 8, 9, 7, 6)
        val k = 3
        val expected = intArrayOf(9, 7, 6, 3, 8)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `K가 배열 크기와 같은 경우 테스트`() {
        val a = intArrayOf(1, 2, 3, 4, 5)
        val k = 5
        val expected = intArrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `K가 배열 크기보다 큰 경우 테스트`() {
        val a = intArrayOf(1, 2, 3, 4)
        val k = 6 // 6 % 4 = 2, 두 번 회전하는 것과 같음
        val expected = intArrayOf(3, 4, 1, 2)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `모든 요소가 같은 배열 테스트`() {
        val a = intArrayOf(1, 1, 1, 1)
        val k = 2
        val expected = intArrayOf(1, 1, 1, 1)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `하나의 요소만 있는 배열 테스트`() {
        val a = intArrayOf(100)
        val k = 10
        val expected = intArrayOf(100)
        assertArrayEquals(expected, solution(a, k))
    }

    @org.junit.Test
    fun `음수가 포함된 배열 테스트`() {
        val a = intArrayOf(-1, -2, -3, -4, -5)
        val k = 1
        val expected = intArrayOf(-5, -1, -2, -3, -4)
        assertArrayEquals(expected, solution(a, k))
    }
//
//    // codility.lesson10.solution 함수를 여기에 직접 정의하거나,
//    // 실제 파일에서 import 해올 수 있습니다.
//    // 여기서는 설명을 위해 파일 내용을 그대로 가져왔다고 가정합니다.
//    private fun codility.lesson10.solution(A: IntArray, K: Int): IntArray {
//        if (A.isEmpty())
//            return A
//
//        val rotation = K % A.size
//        if(rotation == 0)
//            return A
//
//        val result = IntArray(A.size)
//
//        for (i in A.indices) {
//            result[(i + rotation) % A.size] = A[i]
//        }
//        return result
//    }
}