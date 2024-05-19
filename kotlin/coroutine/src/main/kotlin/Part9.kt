import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
    println("end")
}