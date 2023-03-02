package chapter13

class Player {
    var name = "madrigal"
        get() = field.replaceFirstChar { it.uppercase() }
        private set(value) {
            field = value.trim() // убирает пробелы
        }

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter() } -> "The Witness Protection Member"
            name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
            else -> "The Renowned Hero"
        }

    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }

    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }
}