// ������� � ����� 13 ������, ���� ������ ��� ������ ����� ����������

package chapter16

import arrow.core.valid

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

    println(DirectionRotation.values())

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

    private var currentRoom: Room = worldMap[0][0] // ����� ��� Room, ��� ����� �� TownSquare �������� Room
    private var currentPosition = Coordinate(0, 0)

    init {      // ����� �������� ������������� ��� ����������, ������ ���� ���� ��� �������
        narrate("Welcome, adventurer")
        val mortality = if (player.isImmortal) "an immortal" else "a mortal"
        narrate("${player.name}, $mortality, has ${player.healthPoints} health points")
    }

    fun play() {// ����� ��������� ������ ����� �������, ���-�� �� ����, ������� ��� �-��
        while (true) {
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
            else -> narrate("I'm not sure what you're trying to do")
        }
    }
}