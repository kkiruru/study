import com.google.gson.Gson
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.math.BigDecimal
import java.util.Locale

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}


const val JSON_TEXT = """
    {
        "pico": "CUTE"
    }
"""


@Serializable
data class QandaJsonExampe(
    val pico: String,
    val ddeokGu: String = "Cute"
)

data class QandaExampe(
    val pico: String,
    val ddeokGu: String = "Cute"
)

fun main() {
    println(App().greeting)

    val typeToken = object : com.google.gson.reflect.TypeToken<QandaExampe>() {}.type
    val objectFromGson = Gson().fromJson<QandaExampe>(JSON_TEXT, typeToken)
    println(objectFromGson)

    val objectFromKotlinxSerializable = Json.decodeFromString<QandaJsonExampe>(JSON_TEXT)
    println(objectFromKotlinxSerializable)

    val d = 0.3
    println("next ${d.kg}")

    val f = 3.9
    println("next ${f.kg}")

    val g = 3.1
    println("next ${g.kg}")

    val e = 2.1
    println("next ${e.kg}")

    val h = 4.6
    println("next ${h.kg}")


// 사용 예시
    val num = 42317
    val strArray = intToStringArray(num)


    strArray.forEach {
        println(" ${it}")
    }

    val foo = List<Int>(5){0}

    val bar = foo[1]
    println("bar ${bar}")
}


val Double.kg: String
    get() = this.kg()


//private fun Double.kg(): String {
//    val convert = String.format(Locale.ROOT, "%.2f", this)
//    val numberFormat = NumberFormat.getInstance(Locale.US)
//    val number = numberFormat.parse(convert)?.toDouble() ?: 0.0
//
//    val bar = convert.toDouble()
//
////    println("convert ${convert}")
////    println("bar ${bar}")
////    println("bar * 100 ${(bar * 100)}")
//
//    return if (number == 0.0) {
//        "0"
//    } else {
//        val value = (number * 100).toInt()
//        if ((value % 100 == 0)) {
//            String.format(Locale.ROOT, "%.0f", this)
//        } else if ((value % 10) == 0) {
//            String.format(Locale.ROOT, "%.1f", this)
//        } else {
//            String.format(Locale.ROOT, "%.2f", this)
//        }
//    }
//}

open class Out {
    private val aaa = 10
    protected open val bbb = 20
    internal val ccc = 30
    val ddd = 40

    protected class In {
        public val e = 50
    }
}


//class Other : Out() {
//    init {
//        println(aaa)
//        println(bbb)
//        println(ccc)
//        println(ddd)
//        val i = In()
//        println(i.e)
//    }
//
//    override val bbb: Int = 22
//}


//class Other2(o: Out) {
//    init {
//        println(o.aaa)
//        println(o.bbb)
//        println(o.ccc)
//        println(o.ddd)
//        val i = o.In()
//        println(i.e)
//    }
//}

private fun Double.kg(): String {
    try {
        val convert = String.format(Locale.ROOT, "%.2f", this)
        val number = (BigDecimal(convert) * BigDecimal(100)).toInt()
        return if (number == 0) {
            "0"
        } else {
            if (number % 100 == 0) {
                String.format(Locale.ROOT, "%.0f", this)
            } else if (number % 10 == 0) {
                String.format(Locale.ROOT, "%.1f", this)
            } else {
                String.format(Locale.ROOT, "%.2f", this)
            }
        }
    } catch (e: NumberFormatException) {
        return "0"
    }
}

fun intToStringArray(number: Int): Array<String> {
    return number.toString().map { it.toString() }.toTypedArray()
}
