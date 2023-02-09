import kotlin.math.exp

const val pikachu = "thunderbolt"
const val HERO_NAME = "Madrigal"
var playerLevel = 5



fun main() {
    println("Hello again, Kotlin")
    println(15.coerceAtMost(44))

    println("The hero announces her presence to the world.")
    println(HERO_NAME)
    println(playerLevel)

/*    val hasAngeredBarbarians = false
    val hasBefriendedBarbarians = true
    val playerClass = "paladin"*/

    val race = "gnome"
    val faction: String = when (race) {
        "dwarf" -> "Keepers of the Mines"
        "gnome" -> "Tinkerers of the Underground"
        "orc", "human" -> "Free People of the Rolling Hills"
        else -> "Shadow Cabal of the Unseen Realm" // неизвестная раса
    }

    println(faction)

//    при 1 выражении можно использовать if без {} но так лучше делать
//    для выражений, которые содержат не более одной строки
//    var check = false
//    if (playerClass == "paladin") check = true
//    println(check)

//    println("The hero embarks on her journey to locate the enchanted sword.")

/*    val quest: String = if (playerLevel == 1) {
        "Meet Mr. Bubbles in the land of soft things."
    } else if (playerLevel in 2..5) {
        // Проверим возможность дипломатического решения
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
    }*/

//    val quest: String = obtainQuest(playerLevel,
//                                    hasAngeredBarbarians,
//                                    hasBefriendedBarbarians,
//                                    playerClass)

    val totalExperience = 435
    val playerTitle = when (val playerLevel = totalExperience / 100 + 1) {
        1 -> "Apprentice"
        in 2..8 -> "Level $playerLevel Warrior"
        9 -> "Vanquisher of Nogartse"
        else -> "Distinguished Knight"
    }

//    when без аргументов
    val experiencePoints = 853
    val requiredExperiencePoints = 1000


    val levelUpStatus: String = when {
        experiencePoints > requiredExperiencePoints -> {
            "You already leveled up!"
        }
        experiencePoints == requiredExperiencePoints -> {
            "You have enough experience to level up!"
        }
        requiredExperiencePoints - experiencePoints < 20 -> {
//            Игроку остается менее 20 очков опыта
            "You are very close to leveling up!"
        }
        else -> "You need more experience to level up!"
    }

    readBountyBoard()
    println(levelUpStatus)

    println("Player title: $playerTitle")

    println("Time passes...")
    println("The hero returns from her quest.")

    playerLevel++
    println(playerLevel)
    readBountyBoard()
}

private fun readBountyBoard() {
    println("The hero approaches the bounty board. It reads:")
    println(obtainQuest(playerLevel,
                        false,
                        true,
                        "paladin"))
}

private fun obtainQuest(
    playerLevel: Int,
    hasAngeredBarbarians: Boolean,
    hasBefriendedBarbarians: Boolean,
    playerClass: String
): String {

    println(HERO_NAME)
    //    перепишем блок if \ else  на when
    val quest: String = when (playerLevel) {
        1 -> "Meet Mr. Bubbles in the land of soft things."
        in 2..5 -> {
//            проверим возможность дипломатического решения
            val canTalkToBarbarians = !hasAngeredBarbarians &&
                    (hasBefriendedBarbarians || playerClass == "barbarian")
            if (canTalkToBarbarians) "Convince the barbarians to call of their invasion."
            else "Save the town from the barbarian invasion."
        }
        6 -> "Locate the enchanted sword"
        7 -> "Recover the long-lost artifact of creation."
        8 -> "Defeat Nogartse, bringer of death and eater of worlds"
        else -> "There are no quests right now"
    }
    return quest
}