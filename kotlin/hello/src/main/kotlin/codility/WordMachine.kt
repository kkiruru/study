package codility

import kotlinx.coroutines.runBlocking




fun main(): kotlin.Unit = runBlocking<Unit> {
    println(solution("13 DUP 4 POP 5 DUP + DUP + -")) // 예상 결과: 7

    println(solution("1048575 DUP +"))
    println(solutionB("1048575 DUP +"))


    // 20비트 오버플로우 테스트
    println("\n=== 20비트 오버플로우 테스트 ===")
    println("'1048575 DUP +' -> ${solution("1048575 DUP +")}") // 예상: -1 (오버플로우)
    println("'1048574 DUP +' -> ${solution("1048574 DUP +")}") // 예상: 2097148 (정상)
    println("'1048575 1 +' -> ${solution("1048575 1 +")}") // 예상: -1 (오버플로우)
    println("'1048574 1 +' -> ${solution("1048574 1 +")}") // 예상: 1048575 (정상)

    // 음수 테스트
    println("\n=== 음수 테스트 ===")
    println("'5 10 -' -> ${solution("5 10 -")}") // 예상: -1 (음수 결과)
    println("'10 5 -' -> ${solution("10 5 -")}") // 예상: 5 (정상)
    println("'1048575 1048576 -' -> ${solution("1048575 1048576 -")}") // 예상: -1 (음수 결과)

    // 범위 테스트
    println("\n=== 범위 테스트 ===")
    println("'1048576' -> ${solution("1048576")}") // 예상: -1 (최대값 초과)
    println("'1048575' -> ${solution("1048575")}") // 예상: 1048575 (최대값)
    println("'-1' -> ${solution("-1")}") // 예상: -1 (음수)
    println("'0' -> ${solution("0")}") // 예상: 0 (최소값)

    // 복합 연산 테스트
    println("\n=== 복합 연산 테스트 ===")
    println("'100 200 + 300 +' -> ${solution("100 200 + 300 +")}") // 예상: 600
    println("'1048570 5 +' -> ${solution("1048570 5 +")}") // 예상: 1048575
    println("'1048570 6 +' -> ${solution("1048570 6 +")}") // 예상: -1 (오버플로우)

    // 스택 에러 테스트
    println("\n=== 스택 에러 테스트 ===")
    println("'+' -> ${solution("+")}") // 예상: -1 (스택 부족)
    println("'DUP' -> ${solution("DUP")}") // 예상: -1 (스택 비어있음)
    println("'POP' -> ${solution("POP")}") // 예상: -1 (스택 비어있음)
    println("'1 +' -> ${solution("1 +")}") // 예상: -1 (스택 부족)
    println("'1 2 + +' -> ${solution("1 2 + +")}") // 예상: -1 (스택 부족)

    // 경계값 테스트
    println("\n=== 경계값 테스트 ===")
    println("'1048575 1048575 +' -> ${solution("1048575 1048575 +")}") // 예상: -1 (오버플로우)
    println("'1048574 1 +' -> ${solution("1048574 1 +")}") // 예상: 1048575 (정상)
    println("'1048575 0 +' -> ${solution("1048575 0 +")}") // 예상: 1048575 (정상)
    println("'1048575 0 -' -> ${solution("1048575 0 -")}") // 예상: 1048575 (정상)

    // 복잡한 시나리오 테스트
    println("\n=== 복잡한 시나리오 테스트 ===")
    println("'10 20 + 30 + 40 +' -> ${solution("10 20 + 30 + 40 +")}") // 예상: 100
    println("'1048570 5 + DUP +' -> ${solution("1048570 5 + DUP +")}") // 예상: -1 (오버플로우)
    println("'1000 2000 + 3000 + 4000 +' -> ${solution("1000 2000 + 3000 + 4000 +")}") // 예상: 10000



}



private fun solution(S: String): Int {
    // Implement your solution here

    // Implement your solution here

    // println("S >> $S")

    var result = 0

    val numbers = mutableListOf<Long>()
    
    val commands = S.split(" ")

    // println(commands.toString())


    val LIMITED = 1048575

    for(command in commands) {
        // println("command ${command}")
        when(command) {
            "+" -> {
                if (numbers.size < 2) return -1
                val first = numbers.last()
                numbers.removeAt(numbers.lastIndex)

                val second = numbers.last()
                numbers.removeAt(numbers.lastIndex)

                if (LIMITED < first+second)
                    return -1

                numbers.add(first+second)
            }

            "-" -> {
                if (numbers.size < 2) return -1
                val first = numbers.last()
                numbers.removeAt(numbers.lastIndex)

                val second = numbers.last()
                numbers.removeAt(numbers.lastIndex)

                 if (first-second < 0)
                    return -1

                numbers.add(first-second)          
            }

            "DUP" -> {
                if (numbers.isEmpty()) return -1
                numbers.add(numbers.last())
            }

            "POP" -> {
                if (numbers.isEmpty()) return -1
                numbers.removeAt(numbers.size - 1)
            }

            else -> {
                if (command.toLongOrNull() != null) {
                    numbers.add(command.toLong())
                } else {
                    return -1
                }
            }

        }
        // println(": numbers ${numbers}")

    }

    // println("result ${numbers.last()}")
    if (LIMITED < numbers.last().toInt())
        return -1

    if (numbers.last().toInt() < 0)
        return -1

    return numbers.last().toInt()
}


private fun solutionB(S: String): Int {
    if (S.isEmpty()) return -1

    val stack = mutableListOf<Int>()
    val operations = S.split(" ")

    for (operation in operations) {
        when {
            operation.matches(Regex("-?\\d+")) -> {
                // 숫자인 경우 스택에 푸시
                stack.add(operation.toInt())
            }
            operation == "DUP" -> {
                if (stack.isEmpty()) return -1
                stack.add(stack.last())
            }
            operation == "POP" -> {
                if (stack.isEmpty()) return -1
                stack.removeAt(stack.size - 1)
            }
            operation == "+" -> {
                if (stack.size < 2) return -1
                val b = stack.removeAt(stack.size - 1)
                val a = stack.removeAt(stack.size - 1)
                stack.add(a + b)
            }
            operation == "-" -> {
                if (stack.size < 2) return -1
                val b = stack.removeAt(stack.size - 1)
                val a = stack.removeAt(stack.size - 1)
                stack.add(a - b)
            }
            else -> {
                // 알 수 없는 연산
                return -1
            }
        }
    }

    return if (stack.isNotEmpty()) stack.last() else -1
}