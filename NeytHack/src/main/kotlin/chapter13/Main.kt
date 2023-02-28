// начиная с главы 13 классы, буду делать для каждой главы директорию

package chapter13
var heroName: String = ""

fun main() {

    heroName = promptHeroName()
//    chapter12.changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)}, heads to the town square")

    visitTavern()
}

private fun promptHeroName(): String {
    // тут { message... это второй аргумент, параметр принимает функцию, использовав
    // сокращенный синтаксис, мы опускаем круглые скобки для 2ого аргумента, оставим только для первого
    // поэтому мы убираем запятую и таким образом получаем два переданных аргумента
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // Выводит сообщение желтым цветом
        "\u001b[33;1m$message\u001b[0m"
    }

    println("Madrigal")
    return "Madrigal"
}

private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
        else -> "The Renowned Hero"
    }
}