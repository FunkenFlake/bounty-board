const val pikachu = "thunder"

const val HERO_NAME = "Madrigal"



fun main() {
    println("Hello again, Kotlin")
    println(15.coerceAtMost(44))

    println("The hero announces her presence to the world.")
    println(HERO_NAME)

    var playerLevel = 4
    println(playerLevel)

    val hasAngeredBarbarians = false
    val hasBefriendedBarbarians = true
    val playerClass = "paladin"

//    println("The hero embarks on her journey to locate the enchanted sword.")

    val quest: String = if (playerLevel == 1) {
        "Meet Mr. Bubbles in the land of soft things."
    } else if (playerLevel <= 5) {
        // ѕроверим возможность дипломатического решени€
        val canTalkToBarbarians = !hasAngeredBarbarians &&
                (hasBefriendedBarbarians || playerClass == "barbarian")
        if (canTalkToBarbarians) {
            "Convince the barbarians to call of their invasion."
        } else {
            "Save the town from the barbarian invasion."
        }
    } else if (playerLevel == 6) {
        "Locate the enchanted sword."
    } else if (playerLevel == 7) {
        "Recover the long-lost artifact of creation."
    } else if (playerLevel == 8) {
        "Defeat Nogartse, bringer of death and eater of worlds."
    } else {
        "There are no quests right now."
    }

    println("The hero approaches the bounty board. It reads:")
    println(quest)

    println("Time passes...")
    println("The hero returns from her quest.")

    playerLevel++
    println(playerLevel)
}