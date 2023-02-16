package homework.chapter08

import kotlin.random.Random
import kotlin.random.nextInt

var narrationModifier: (String) -> String = { it }

inline fun narrate(
    message: String,
    modifier: (String) -> String = { narrationModifier(it) },
) {
    println(modifier(message))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String
    val randomInt = Random.nextInt(7..7)
    println(randomInt)
    when (randomInt) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExclamationPoints = 3
                message.uppercase() + "!".repeat(numExclamationPoints)
            }
        }
        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "... ")
            }
        }
        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }
        4 -> {
            // narrationsGiven определяется за пределами лямбды, хотя она может
            // обращаться к ней и изменять её.
            var narrationsGiven = 0
            mood = "like sending an itemized bill"
            modifier = { message ->
                narrationsGiven++
                "$message.\n(I have narrated $narrationsGiven things)"
            }
        }
        5 -> {
            // lazy from homework
            mood = "lazy"
            modifier = { message ->
                message.take(Random.nextInt(5..(message.count())))
            }
        }
        6 -> {
            // leet from homework
            mood = "leet"
            modifier = { message ->
                message.replace("l", "1")
                message.replace("L", "1")
                message.replace("e", "3")
                message.replace("E", "3")
                message.replace("t", "7")
                message.replace("T", "7")
                message.replace("o", "0")
            }
        }
        7 -> {
            mood = "poetic"
            modifier = { message ->
                "dunno"
            }
        }

        else -> {
            mood = "professional"
            modifier = { message ->
                "$message."
            }
        }
    }

    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}