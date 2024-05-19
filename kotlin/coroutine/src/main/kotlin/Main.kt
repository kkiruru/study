package org.example


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//fun main() {
//    val name = "Kotlin"
//    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
//    // to see how IntelliJ IDEA suggests fixing it.
//    println("Hello, " + name + "!")
//
//    for (i in 1..5) {
//        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//        println("i = $i")
//    }
//}

fun main(args: Array<String>) = runBlocking<Unit> {
//    val squares = produceSquares(5)
//    squares.consumeEach { println(it) }
//    println("Done")
//
//    val numbers = produceNumbers(5)
//    val doubledNumbers = produceDoubles(numbers)
//    doubledNumbers.consumeEach {
//        println(it)
//    }
//    println("Done")
//
//    var cur = numbersFrom(2)
//    for ( i in 1..10) {
//        val prime = cur.receive()
//        println(prime)
//        cur = filter(cur, prime)
//    }
//
//    coroutineContext.cancelChildren()

//    val producer = produceNumbers()
//    repeat(5) {
//        println("repeat ${it}")
//        launchProcessor(it, producer)
//    }
//    delay(950L)
//    producer.cancel()


//    val time = measureTimeMillis {
//        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
//        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
//
//        one.start()
//        two.start()
//
//        println("The answer is ${one.await() + two.await()}")
//    }
//
//    println("Completed in $time ms")

//    val time2 = measureTimeMillis {
//        val one = somethingUsefulOneAsync()
//        val two = somethingUsefulTwoAsync()
//
//        runBlocking {
//            println("The answer is ${one.await() + two.await()}")
//        }
//    }

//    println("Completed in $time2 ms")
//
//    foo()
//
//    val time3 = measureTimeMillis {
//        println("The answer is ${concurrentSum()}")
//    }
//    println("Completed in $time3 ms")

    try {
        val time = measureTimeMillis {
            println("The answer is ${failedConcurrentSum()}")
        }
        println("Completed in $time ms")
    } catch (throwable: Throwable) {
        println("Computation failed with $throwable")
    }


    println("Done")
}

suspend fun failedConcurrentSum() : Int = coroutineScope {
    val one = async {
        try {
            delay(Long.MAX_VALUE)
            doSomethingUsefulOne()
        } finally {
            println("First child was canceled.")
        }
    }
    val two = async<Int> {
        println("Second child throw an exception.")
        doSomethingUsefulTwo()
        throw ArithmeticException("Exception on purpose.")
    }

    one.await() + two.await()
}


suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }

    one.await() + two.await()
}

fun foo() {
    val time2 = measureTimeMillis {
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()

        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time2 ms")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// The result type of somethingUsefulTwoAsync is Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

suspend fun doSomethingUsefulOne(): Int {
    println("doSomethingUsefulOne >>")
    delay(1000L)
    println("doSomethingUsefulOne <<")
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}

fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while(true) {
        println("send ${x}")
        send(x++)
    }
}

fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers) {
        println("filter ${x} : ${numbers}")
        if (x % prime != 0) {
            println("send in filter ${x}")
            send(x)
        }
    }
}


fun CoroutineScope.produceSquares(max: Int): ReceiveChannel<Int> = produce {
    for (x in 1..max) {
        send(x * x)
    }
}

fun CoroutineScope.produceNumbers(max: Int): ReceiveChannel<Int> = produce {
    for (x in 1..max) {
        send(x)
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    println("launchProcessor #$id")
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}

fun CoroutineScope.produceDoubles(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    numbers.consumeEach { send((it * 2).toString()) }
}

fun CoroutineScope.produceNumbers(): ReceiveChannel<Int> = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}