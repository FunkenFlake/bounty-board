// ������� � ����� 13 ������, ���� ������ ��� ������ ����� ����������

package chapter19

import arrow.core.valid
import kotlin.system.exitProcess

// ����������� ������ ������� ��������������� ���, �������
// ���������� player ����� ��� Player
lateinit var player: Player

fun main() {
    narrate("Welcome to NyetHack!")
    val playerName = promptHeroName()
    player = Player(playerName)
     changeNarratorMood()

/*    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora", 15))
    val lootBoxTwo: LootBox<Gemstones> = LootBox(Gemstones(150))
    // val lootBoxTwo = LootBox(Gemstone(150)) ���������� ��� ����

    repeat(2) {
        narrate(
            lootBoxOne.takeLoot()?.let {
                "The hero retrieves ${it.name} from the box"
            } ?: "The box is empty"
        )
    }*/

//    player.prophesize()
//    player.castFireball()
//    player.prophesize()

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

    val input = readLine()
    require(input != null && input.isNotEmpty()) {
        "The hero must have a name."
    }

    return input
}

object Game {   // ������, �� �� �����, ��� � �����, ������ � 1 ����������
                // ������� �������� ��������, ��� �� �� ��� ������� �����������

    private val worldMap = listOf(
        listOf(TownSquare(), Tavern(), Room("Back Room")),
        listOf(MonsterRoom("A Long Corridor"), Room("A Generic Room")),
        listOf(MonsterRoom("The Dangeon"))
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
        // ��� ��������� ��������� ������, ���� �-�� ��������� � Ext
        val newPosition = currentPosition move direction //direction.updateCoordinate(currentPosition)
        // ��� ��������� ����������� �-��, ��������� � Ext
        val newRoom = worldMap[newPosition] //.getOrNull(newPosition.y)?.getOrNull(newPosition.x)

        if (newRoom != null) {
            narrate("The hero moves ${direction.name}")
            currentPosition = newPosition
            currentRoom = newRoom
        } else {
            narrate("You cannot move ${direction.name}")
        }
    }

    fun fight() {
        val monsterRoom = currentRoom as? MonsterRoom
        val currentMonster = monsterRoom?.monster
        if (currentMonster == null) {
            narrate("There's nothing to fight here")
            return
        }

        var combatRound = 0
        val previousNarrationModifier = narrationModifier
        narrationModifier = { it.addEnthusiasm(enthusiasmLevel = combatRound)}

        while (player.healthPoints > 0 && currentMonster.healthPoints > 0) {
            combatRound++

            player.attack(currentMonster)
            if (currentMonster.healthPoints > 0) {
                currentMonster.attack(player)
            }
            Thread.sleep(1000)
        }

        narrationModifier = previousNarrationModifier

        if (player.healthPoints <= 0) {
            narrate("You have been defeated! Thanks for playing")
            exitProcess(0)
        } else {
            narrate("${currentMonster.name} has been defeated")
            monsterRoom.monster = null
        }
    }

    fun takeLoot() {
        val loot = currentRoom.lootBox.takeLoot()
        if (loot == null) {
            narrate("${player.name} approaches the loot box, but it is empty")
        } else {
            narrate("${player.name} now has a ${loot.name}")
            player.inventory += loot
        }
    }

    fun sellLoot() {
        when (val currentRoom = currentRoom) {
            is TownSquare -> {
                player.inventory.forEach { item ->
                    if (item is Sellable) {
                        val sellPrice = currentRoom.sellLoot(item)
                        narrate("Sold ${item.name} for $sellPrice gold")
                        player.gold += sellPrice
                    } else {
                        narrate("Your ${item.name} can't be sold")
                    }
                }
                player.inventory.removeAll { it is Sellable }
            }
            else -> narrate("You cannot sell anything here")
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processCommand() = when (command.lowercase()) {
            "fight" -> fight()
            "exit" -> exitProcess(0)
            "take" -> {
                if (argument.equals("loot", ignoreCase = true)) {
                    takeLoot()
                } else {
                    narrate("I don't know what you're trying to take")
                }
            }
            "sell" -> {
                if (argument.equals("loot", ignoreCase = true)) {
                    sellLoot()
                } else {
                    narrate("I don't know what you're trying to sell")
                }
            }
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