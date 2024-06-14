import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import java.rmi.server.LogStream.log

//fun foo(): List<Int> = listOf(1,2,3)

suspend fun foo() : Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(100)
        yield(i)
    }
}

suspend fun bar() : Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}


fun main() = runBlocking {
    foo().forEach{ value -> println(value) }
    
//    launch {
//        for (k in 1..3) {
//            println("I'm not blocked $k")
//            delay(100)
//        }
//    }

    println("Calling bar...")
    val flow = bar()
    println("Calling colect...")
    flow.collect{ value ->
        println(value)
    }
    println("Calling colect again...")
    flow.collect{ value ->
        println(value)
    }

    println("\nWith Timeout..")

    withTimeoutOrNull(250) {
        bar().collect { value -> println(value)}
    }

    println("\nasFlow..")

    (1..3).asFlow().collect{ value -> println(value)}

    println("\n플로우 중간 연산자 (Intermediate flow operators)")

    (1..3).asFlow()
    .map { request -> performRequest(request) }
    .collect{ response -> println(response) }

    (1..3).asFlow()
    .transform { request ->
        emit("Making request $request")
        emit(performRequest(request))
    }
    .collect { response -> println(response) }

    println("\n크기 제한 연산자 (Size-limiting operators)")

    numbers()
        .take(2)
        .collect{ value -> println(value)}

    println("\n플로우 종단 연산자 (Terminal flow operators)")

    val sum = (1..5).asFlow()
    .map {
        println("map $it -> ${it * it}")
        it * it }
    .reduce { a, b ->
        println("reduce $a, $b -> ${a + b}")
        a + b
    }
    println(sum)


    println("\n플로우는 순차적이다 (Flows are sequential)")

    (1..5).asFlow()
    .filter {
        println("Filter $it")
        log("Filter $it")
        it % 2 == 0
    }.map {
        println("Map $it")
        "string $it"
    }.collect {
        println("Collect $it")
    }

    annotaion()

    println("end")
}

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "response $request"
}

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)

        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

fun annotaion() {
    val content = "기사님이 방문했지만 입력한 방법으로 출입하지 못한 경우, 방문비용 3,800원이 결제됩니다. 꼭 방문 가능한 정확한 출입방법을 입력해주세요."
    val emphasize = "방문비용 3,800원"

    SpannableText(content, emphasize)
}



fun SpannableText(content: String, emphasize: String?) : List<String> {

    emphasize?.let {
        val first = content.indexOf(it)
        val last = content.lastIndexOf(it)

        val a = content.substring(0 until first)
        val b = content.substring(first until last)
        val c = content.substring(last)

        println(a)
        println(b)
        println(c)


        val d = content.substringBefore(emphasize)
        val e = content.substringAfter(emphasize)
        println(d)
        println(emphasize)
        println(e)
    }

    return emptyList()

}