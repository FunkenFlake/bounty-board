// начина€ с главы 13 классы, буду делать дл€ каждой главы директорию

package chapter16

// определение класса создает соответствующий тип, поэтому
// переменна€ player имеет тип Player
lateinit var player: Player

fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
    // changeNarratorMood()
//    player.prophesize()
//    player.castFireball()
//    player.prophesize()

//    repeat(15) {
//        damageSword()
//    }

    Game.play()
}

private fun promptHeroName(): String {
    // тут { message... это второй аргумент, параметр принимает функцию, использовав
    // сокращенный синтаксис, мы опускаем круглые скобки дл€ 2ого аргумента, оставим только дл€ первого
    // поэтому мы убираем зап€тую и таким образом получаем два переданных аргумента
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // ¬ыводит сообщение желтым цветом
        "\u001b[33;1m$message\u001b[0m"
    }

    println("Madrigal")
    return "Madrigal"
}
// for fun
private fun damageSword() {
    Thread.sleep((200..1200).random().toLong())
/*    narrate("Damage Sword: ${player.weaponDamage(
        attackPower = (35..45).random(),
        skill = (12..14).random(),
        bonusDamage = (3..9).random())}")*/
    print("|")
}

object Game {   // объект, то же самое, что и класс, только в 1 экземпл€ре
                // поэтому называют синглтон, так же он сам создает конструктор

    private var currentRoom: Room = TownSquare() // имеет тип Room, так можно тк TownSquare подкласс Room

    init {      // можно объ€вить инициализацию дл€ выполнени€, какого нить кода при запуске
        narrate("Welcome, adventurer")
        val mortality = if (player.isImmortal) "an immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    }

    fun play() {// чтобы запустить объект нужно вызвать, что-то из него, вызовем эту ф-ию
        while (true) {
            // Game
            narrate("${player.name} of ${player.hometown}, ${player.title}," +
                    " is in ${currentRoom.description()}")
            currentRoom.enterRoom()

            print("> Enter your command: ")
            println("Last command: ${readLine()}")
        }
    }
}