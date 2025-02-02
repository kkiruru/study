import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
//    val channel = Channel<Int>()
//
//    val producer1 = launch {
//        println("producer1: ${Thread.currentThread().name}")
//        for (i in 1..5) {
//            delay(300L)
//            channel.send(i)
//            println("Send1: $i")
//        }
//    }
//
//    val producer2 = launch {
//        println("producer2: ${Thread.currentThread().name}")
//        for (i in 1..5) {
//            delay(600L)
//            channel.send(i * 10)
//            println("Send2: ${i * 10}")
//        }
//    }
//
//    launch {
//        println("consumer: ${Thread.currentThread().name}")
//        for (value in channel) {
//            println("Receive: $value")
//        }
//    }
//
//    producer1.join()
//    producer2.join()
//    channel.close()

    joinDate(1)
    joinDate(12)
    joinDate(123)
    joinDate(1234)
    joinDate(12345)

}

fun joinDate(joinDate: Int) {
    val joinString = String.format("%03d", joinDate)
    println(joinString)

    for(number in joinString) {
        println(number)
    }
}