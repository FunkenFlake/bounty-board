package chapter09.homework

import chapter12.narrate
import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"
private const val welcomeMessage = "*** Welcome to $TAVERN_NAME ***"

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

var uniqueTypes = setOf<String>()

private val menuItems = List(menuData.size) { index ->

    val (type, name, price) = menuData[index].split(",")
    val sep = "."
    val repeatOfSep = welcomeMessage.count() + 1 - "$name $price".count()
    uniqueTypes += type

    "$type$name${sep.repeat(repeatOfSep)}$price"
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME\n")
    narrate(welcomeMessage)

    uniqueTypes.forEach { uniqueType ->
        val repeatOfSpace = (welcomeMessage.count() / 2) - (uniqueType.count() / 2) - 4
        println("${" ".repeat(repeatOfSpace)} ~[$uniqueType]~")
        menuItems.forEach { menuItem ->
            if (uniqueType in menuItem) println(menuItem.replace(uniqueType, ""))
        }
    }
}