package chapter09.homework

import chapter12.narrate


var heroName: String = "Madrigal"

fun main() {

/*    chapter12.getHeroName = promptHeroName()
//    chapter12.changeNarratorMood()
    chapter12.narrate("$chapter12.getHeroName, ${createTitle(chapter12.getHeroName)}, heads to the town square")*/

    visitTavern()
}

private fun promptHeroName(): String {
    // тут { message... это второй аргумент, параметр принимает функцию, использовав
    // сокращенный синтаксис, мы опускаем круглые скобки дл€ 2ого аргумента, оставим только дл€ первого
    // поэтому мы убираем зап€тую и таким образом получаем два переданных аргумента
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // ¬ыводит сообщение желтым цветом
        "\u001b[33;1m$message\u001b[0m"
    }

/*    val input = readLine()
    require(input != null && input.isNotEmpty()) {
        "The hero must have a name."
    }
    return input*/
    println("Madrigal")
    return "Madrigal"
}

/*
private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
        else -> "The Renowned Hero"
    }
}*/
