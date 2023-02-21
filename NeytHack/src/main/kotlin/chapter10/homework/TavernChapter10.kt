package chapter10.homework

import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironforge", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private val menuItemPrices = List(menuData.size) { index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

private val menuItemTypes = List(menuData.size) { index ->
    val(type, name, _) = menuData[index].split(",")
    name to type
}.toMap()

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME\n")
    narrate("There are several items for sale:")
    menuItems.forEach { menuItem ->
        println(menuItem)
    }

    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50,
    )

    while(patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        patronGold += patronName to 13.0
    }

    narrate("\n$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    println(patronGold)

    repeat(3) {
        placeOrder(patrons.random(), menuItems, patronGold)
    }

    println("\n$patronGold")
    displayPatronBalance(patronGold)

}

private fun placeOrder(
    patronName: String,
    menuItemsNames: List<String>,
    patronGold: MutableMap<String, Double>
) {

    var totalPrice = 0.0

    narrate("\n$patronName speaks with $TAVERN_MASTER to place an order")
    var menuItemName: List<String> = listOf()


    repeat((1..3).random()) {
        val someItem = menuItemsNames.random()
        val itemPrice = menuItemPrices.getValue(someItem)
        menuItemName += someItem
        totalPrice += itemPrice
    }

    if (totalPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        menuItemName.forEach {item ->
            val action = when (menuItemTypes[item]) {
                "shandy", "elixir" -> "pours"
                "meal" -> "serves"
                else -> "hands"
            }
            narrate("$TAVERN_MASTER $action $patronName a \u001b[33;1m$item\u001b[0m")
        }
        narrate("$patronName \u001B[32;1mpays\u001B[0m $TAVERN_MASTER \u001b[33;1m${"%.2f".format(totalPrice)}\u001b[0m gold")
        patronGold[patronName] = patronGold.getValue(patronName) - totalPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + totalPrice
    } else {
        narrate("$TAVERN_MASTER says: \"\u001B[31;1mYou need more coin\u001B[0m for a \u001B[33;1m${menuItemName.joinToString()}\u001B[0m\"")
    }
}

private fun displayPatronBalance(patronGold: Map<String, Double>) {
    narrate("\n$heroName intuitively knows how much money each patron has:")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}