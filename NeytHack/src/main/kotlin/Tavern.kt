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

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME\n")
    narrate("There are several items for sale:")
    println(menuItems.joinToString())

    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50,
    )

    while(patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        patronGold += patronName to 6.0
    }

//    println(patrons[0]) // можно получать индекс через функцию .get(0)
//    println(patrons.getOrElse(5) { "Unknown Patron" })
//    println(patrons.getOrNull(5) ?: "Unknown Patron")



  /*  val eliMessage = if (patrons.contains("Eli")) {
        "$TAVERN_MASTER says: Eli's in the back playing cards"
    } else {
        "$TAVERN_MASTER seys: Eli isn't here"
    }

    println(eliMessage)

    val othersMessage = if (patrons.containsAll(listOf("Sophie", "Mordoc"))) {
        "$TAVERN_MASTER says: Sophie and Mordoc are seated by the stew kettle"
    } else {
        "$TAVERN_MASTER says: Sophie and Mordoc aren't with each other right now"
    }

    println(othersMessage)*/

/*    for (patron in patrons) {
        println("Good evening, $patron")
    }*/

    // заменим цикл for на forEach
    // forEach перебирает все элементы списка и передает каждый лябда-выражению
    // переданному в аргументе.
/*    patrons.forEach { patron ->
        println("Good evening, $patron")
    }*/

    // для работы с индексами используем forEachIndexed
/*    patrons.forEachIndexed { index, patron ->
        println("Good evening, $patron - you're #${index + 1} in line")
        placeOrder(patron, menuItems.random())
    }*/

/*    menuData.forEachIndexed { index, data ->
        println("$index : $data")
    }*/

    // можно и строку итерировать
/*    "someText".forEach { symbol ->
        println("$symbol UU")
    }*/

    narrate("\n$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    println(patronGold)

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }
    println("\n$patronGold")

}

private fun placeOrder(
    patronName: String,
    menuItemName: String,
    patronGold: MutableMap<String, Double>
) {
    val itemPrice = 1.0

    narrate("\n$patronName speaks with $TAVERN_MASTER to place an order")
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
    }


}