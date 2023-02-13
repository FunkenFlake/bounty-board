import java.util.Objects.equals
import kotlin.math.exp

const val pikachu = "thunderbolt"
const val HERO_NAME = "Madrigal"
var playerLevel = 0



fun main() {
    println("Hello again, Kotlin")
    println(15.coerceAtMost(44))

    println("$HERO_NAME announces her presence to the world.")
    println("What level is $HERO_NAME?")
    playerLevel = readLine()?.toIntOrNull() ?: 0
    println("$HERO_NAME's level is $playerLevel")

//    println(equals(pikachu, HERO_NAME))


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
    println("$HERO_NAME returns from her quest.")

    playerLevel++
    println(playerLevel)
    readBountyBoard()
}

private fun readBountyBoard() {
    val quest: String? = obtainQuest(playerLevel)

    val message: String = quest?.replace("Nogartse", "xxxxxxxx")
        ?.let{ censoredQuest ->
            """
            |$HERO_NAME approaches the bounty board. It reads:
            |   "$quest"
            |   "$censoredQuest"
            """.trimMargin()
        } ?: "$HERO_NAME approaches the bounty board, but it is blank."
    println(message)
}

// Можно еще проверить регистр на замену
// "[Nn]ogartse".toRegex()


// тк функция содержит одно выражение мы опускаем return и {}, вместо них ставим =
// так же можно не указывать тип возвращаемого значения (просто для читаемости лучше его указать)
private fun obtainQuest(
    playerLevel: Int,
    hasBefriendedBarbarians: Boolean = true,
    playerClass: String = "paladin",
    hasAngeredBarbarians: Boolean = false): String? =

    //    перепишем блок if \ else  на when
    when (playerLevel) {
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
        else -> null
    }

private fun someFun(): String {
    TODO("something from TODO functions")
    println("some text from someFun")
}