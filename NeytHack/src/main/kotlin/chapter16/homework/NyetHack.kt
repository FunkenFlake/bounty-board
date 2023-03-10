// ������� � ����� 13 ������, ���� ������ ��� ������ ����� ����������

package chapter16.homework

import chapter16.homework.Game.miniMap

// ����������� ������ ������� ��������������� ���, �������
// ���������� player ����� ��� Player
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

    println(DirectionRotation.BS)

    Game.play()
}

private fun promptHeroName(): String {
    // ��� { message... ��� ������ ��������, �������� ��������� �������, �����������
    // ����������� ���������, �� �������� ������� ������ ��� 2��� ���������, ������� ������ ��� �������
    // ������� �� ������� ������� � ����� ������� �������� ��� ���������� ���������
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // ������� ��������� ������ ������
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

object Game {   // ������, �� �� �����, ��� � �����, ������ � 1 ����������
                // ������� �������� ��������, ��� �� �� ��� ������� �����������

    private val worldMap = listOf(
        listOf(TownSquare(), Tavern(), Room("Back Room")),
        listOf(Room("A Long Corridor"), Room("A Generic Room")),
        listOf(Room("The Dangeon"))
    )

    private val miniMap = mutableListOf(
        listOf("[1:1]", "[1:2]", "[1:3]"),
        listOf("[2:1]", "[2:2]"),
        listOf("[3:1]")
    )

    private var currentRoom: Room = worldMap[0][0] // ����� ��� Room, ��� ����� �� TownSquare �������� Room
    private var currentPosition = Coordinate(0, 0)

    private var playGame = true

    init {      // ����� �������� ������������� ��� ����������, ������ ���� ���� ��� �������
        narrate("Welcome, adventurer")
        val mortality = if (player.isImmortal) "an immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    }

    fun play() {// ����� ��������� ������ ����� �������, ���-�� �� ����, ������� ��� �-��
        while (playGame) {
            // Game
            narrate("${player.name} of ${player.hometown}, ${player.title}," +
                    " is in ${currentRoom.description()}")
            currentRoom.enterRoom()

            print("> Enter your command: ")
            GameInput(readLine()).processCommand()
        }
    }

    fun move(direction: Direction) {
        val newPosition = direction.updateCoordinate(currentPosition)
        val newRoom = worldMap.getOrNull(newPosition.y)?.getOrNull(newPosition.x)

        if (newRoom != null) {
            narrate("The hero moves ${direction.name}")
            currentPosition = newPosition
            currentRoom = newRoom
        } else {
            narrate("You cannot move ${direction.name}")
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.lowercase()) {
            "move" -> {
                val direction = Direction.values()
                    .firstOrNull { it.name.equals(argument, ignoreCase = true) }
                if (direction != null) {
                    move(direction)
                } else {
                    narrate("I don't know what direction that is")
                }
            }

            "cast" -> {
                // ����� �������� ��� frostball , �� ���-�� �� ����������
                val spell = SpellBook.values()
                    .firstOrNull { it.name.equals(argument, ignoreCase = true) }

                if (spell != null) {
                    player.castFireball()
                } else {
                    narrate("I don't know what spell that is")
                }
            }

            "prophesize" -> player.prophesize()

            "map" -> {
                miniMap[0][1]
                println(miniMap[currentPosition.x][currentPosition.y])

                miniMap.forEachIndexed { index, it ->
                    if (currentPosition.y == index) {
                        println("$index, $it")
                        it.forEachIndexed { index, it ->
                            if (currentPosition.x == index) {
                                println("$index, $it")
//                                miniMap.add(index, "jj")
                            }
                        }
                    }
//                    println("$index, $it")

                }
            }

            "ring" -> narrate("����� ���� � ������� ����� ���-�� ���")

            "exit" -> playGame = false

            else -> narrate("I'm not sure what you're trying to do")
        }
    }
}