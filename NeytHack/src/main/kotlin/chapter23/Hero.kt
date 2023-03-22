// for Jhava (main/java/Jhava)
@file:JvmName("Hero")

package chapter23

import Jhava
import java.io.IOException

fun main() {
    val adversary = Jhava()
    println(adversary.utterGreeting())

    adversary.offerFood()

    try {
        adversary.extendHandInFriendship()
    } catch (e: Exception) {
        println("Begone, foul beast!")
    }


    val friendshipLevel = adversary.determineFriendshipLevel()
    println(friendshipLevel?.lowercase() ?: "It's complicated")

    val adversaryHitPoints: Int = adversary.hitPoints

    println(adversaryHitPoints.coerceAtMost(100))
    println(adversaryHitPoints.javaClass)

    adversary.greeting = "Hello, Hero."
    println(adversary.utterGreeting())

}

val translator = { utterance: String ->
    println(utterance.lowercase().replaceFirstChar { it.uppercase() })
}

fun makeProclamation() = "Greetings, beast!"

@JvmOverloads
fun handOverFood(leftHand: String = "berries", rightHand: String = "beef") {
    println("Mmmmm... you hand over some delicious $leftHand and $rightHand.")
}
@Throws(IOException::class)
fun acceptApology() {
    throw IOException()
}

class Spellbook {
    @JvmField
    val spells = listOf("Magic Ms. L", "Lay on Hans")

    companion object {
        @JvmField
        var maxSpellCount = 10

        @JvmStatic
        fun getSpellbookGreeting() = println("I am the Great Grimoire!")
    }
}