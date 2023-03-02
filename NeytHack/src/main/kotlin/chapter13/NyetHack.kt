// начиная с главы 13 классы, буду делать для каждой главы директорию

package chapter13

// определение класса создает соответствующий тип, поэтому
// переменная player имеет тип Player
val player = Player()

fun main() {
    narrate("${player.name} is ${player.title}")
    player.changeName("Aurelia")
    narrate("${player.name}, ${player.title}, heads to the town square")

    visitTavern()
    player.castFireball()
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