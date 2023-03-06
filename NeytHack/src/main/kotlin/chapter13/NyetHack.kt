// начиная с главы 13 классы, буду делать для каждой главы директорию

package chapter13

// определение класса создает соответствующий тип, поэтому
// переменная player имеет тип Player
lateinit var player: Player

fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    // changeNarratorMood()
    player.prophesize()
    val mortality = if (player.isImmortal) "an immortal" else "a mortal"
    narrate("${player.name} of ${player.hometown}, ${player.title}, heads to the town square")
    narrate("${player.name}, $mortality, has ${player.healthPoints} health points")

    visitTavern()
    player.castFireball()
    player.prophesize()

    repeat(11) {
        narrate("Damage Sword: ${player.weaponDamage(
            attackPower = (35..45).random(),
            skill = (12..14).random(),
            bonusDamage = (3..9).random())}")
    }
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