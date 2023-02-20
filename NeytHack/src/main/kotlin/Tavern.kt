import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstName = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastName = setOf("Ironforge", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")

private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are several items for sale:")
    println(menuItems.joinToString())

    val patrons: MutableSet<String> = mutableSetOf()
    val patronGold = mapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50,
    )

    while(patrons.size < 10) {
        patrons += "${firstName.random()} ${lastName.random()}"
    }

    println(patronGold)

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

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random())
    }

}

private fun placeOrder(patronName: String, menuItemName: String) {
    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    narrate("$TAVERN_MASTER hands $patronName a $menuItemName")
}