package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.util.*

var foo = 0

fun CoroutineScope.fizz() = produce<String> {
    while (true) {
        delay(300)
        send("Fizz ${foo}")
        foo++
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while (true) {
        delay(500)
        send("Buzz ${foo}")
        foo++
    }
}

fun main(args: Array<String>) = runBlocking {
//    val fizz = fizz()
//    val buzz = buzz()
//
////    repeat(7) {
////        selectFizzBuzz(fizz, buzz)
////    }
//
//    val a = produce<String> {
//        repeat(4) { send("Hello $it") }
//    }
//
//    val b = produce<String> {
//        repeat(4) { send("World! $it") }
//    }
//    repeat(8) {
//        println(selectAorB2(a, b))
//    }
//
//    coroutineContext.cancelChildren()

    val list = asyncStringList()
    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            println(">> $index await")
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }
    println(result)
    val countActive = list.count { it.isActive }
    println("$countActive coroutines are still active")
}

suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    select<Unit> {
        fizz.onReceive { value ->
            println("fizz -> $value")
        }

        buzz.onReceive { value ->
            println("buzz -> $value")
        }
    }
}

suspend fun selectAorB(a: ReceiveChannel<String?>, b: ReceiveChannel<String?>) : String =
    select<String> {
        a.onReceive { value ->
            if (value == null) {
                "Channel 'a' is closed"
            } else {
                "a -> '$value'"
            }
        }
        b.onReceive { value ->
            if (value == null) {
                "Channel 'b' is closed"
            } else {
                "b -> '$value'"
            }
        }
    }


suspend fun selectAorB2(a: ReceiveChannel<String?>, b: ReceiveChannel<String>): String =
    select<String> {
        a.onReceiveCatching { value ->
            if (value.isClosed)
                "Channel 'a' is closed"
            else
                "a -> ${value.getOrNull()}"
        }
        b.onReceiveCatching { value ->
            if (value.isClosed)
                "Channel 'b' is closed"
            else
                "b -> ${value.getOrNull()}"
        }
    }


fun CoroutineScope.asyncString(time: Int) = async {
    println(">> asyncString $time")
    delay(time.toLong())
    "Waited for $time ms"
}

fun CoroutineScope.asyncStringList(): List<Deferred<String>> {
    val random = Random()
    return List(11) { asyncString(random.nextInt(1000))}
}

