// Раздел для любознательный 8 глава: ссылки на функции



package chapter08

import narrate
import changeNarratorMood

fun main() {

    // ::makeYellow - передаем в виде аргумента ссылку на функцию
    // ссылка на ф-ию преобразует обычную ф-ию (fun), в значение с типом функции.
    narrate("A hero enters the town of Kronstadt. What is their name?", ::makeYellow)

    val heroName = readLine()
    require(heroName != null && heroName.isNotEmpty()) {
        "The hero must have a name."
    }

    changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)}, heads to the town square")
}

private fun makeYellow(message: String) = "\u001B[33;1m$message\u001B[0m"

private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
        else -> "The Renowned Hero"
    }
}