package chapter12

import arrow.core.Either
import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironforge", "Fernsworth", "Baggins", "Downstrider")

private val menuData = File("data/tavern-menu-data.txt")
    .readText()
    .split("\n")
    .map { it.split(",")}

private val menuItems = menuData.map { (_, name, _) -> name }

private val menuItemPrices = menuData.associate { (_, name, price) ->
    name to price.toDouble()
}

private val menuItemTypes = menuData.associate { (type, name, _) ->
    name to type
}

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME\n")
    narrate("There are several items for sale:")
    println(menuItems.joinToString())

    val patrons: MutableSet<String> = firstNames.shuffled()
        .zip(lastNames.shuffled()) { firstName, lastName ->
            "$firstName $lastName"}.toMutableSet()

    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50,
        *patrons.map { it to 6.00 }.toTypedArray()
    )



    // заменяем на строку выше *patrons.map {......
/*    patrons.forEach { patronName ->
        patronGold += patronName to 6.0
    }*/

//    println(patrons[0]) // можно получать индекс через функцию .get(0)
//    println(patrons.getOrElse(5) { "Unknown Patron" })
//    println(patrons.getOrNull(5) ?: "Unknown Patron")



  /*  val eliMessage = if (patrons.contains("Eli")) {
        "$chapter12.TAVERN_MASTER says: Eli's in the back playing cards"
    } else {
        "$chapter12.TAVERN_MASTER seys: Eli isn't here"
    }

    println(eliMessage)

    val othersMessage = if (patrons.containsAll(listOf("Sophie", "Mordoc"))) {
        "$chapter12.TAVERN_MASTER says: Sophie and Mordoc are seated by the stew kettle"
    } else {
        "$chapter12.TAVERN_MASTER says: Sophie and Mordoc aren't with each other right now"
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
        chapter12.placeOrder(patron, chapter12.menuItems.random())
    }*/

/*    chapter12.menuData.forEachIndexed { index, data ->
        println("$index : $data")
    }*/

    // можно и строку итерировать
/*    "someText".forEach { symbol ->
        println("$symbol UU")
    }*/

    narrate("\n$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    val itemOfDay = patrons.flatMap { getFavoriteMenuItems(it) }.random()
    narrate("The item of the day is the $itemOfDay")

    println(patronGold)

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }
    println("\n$patronGold")
    displayPatronBalance(patronGold)

/*    val departingPatrons: List<String> = patrons
        .filter { patron -> patronGold.getOrDefault(patron, 0.0) < 4.0 }
    patrons -= departingPatrons
    patronGold -= departingPatrons
    departingPatrons.forEach { patron ->
        chapter12.narrate("$chapter12.getHeroName sees $patron departing the tavern")
    }*/

    // перепишем блок выше, используем also
    patrons.filter { patron -> patronGold.getOrDefault(patron, 0.0) < 4.0 }
        .also { departingPatrons ->
            patrons -= departingPatrons
            patronGold -= departingPatrons
        }.forEach { patron ->
            narrate("$heroName sees $patron departing the tavern")
        }

    narrate("There are still some patrons in the tavern")
    narrate(patrons.joinToString())
}

private fun getFavoriteMenuItems(patron: String): List<String> {
    return when (patron) {
        "Alex Ironfoot" -> menuItems.filter { menuItem ->
            menuItemTypes[menuItem]?.contains("dessert") == true
        }
        else -> menuItems.shuffled().take(Random.nextInt(1..2))
    }
}

private fun placeOrder(
    patronName: String,
    menuItemName: String,
    patronGold: MutableMap<String, Double>
) {
    val itemPrice = menuItemPrices.getValue(menuItemName)

    narrate("\n$patronName speaks with $TAVERN_MASTER to place an order")
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        val action = when (menuItemTypes[menuItemName]) {
            "shandy", "elixir" -> "pours"
            "meal", -> "serves"
            else -> "hands"
        }
        narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold")
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
    }
}

private fun displayPatronBalance(patronGold: Map<String, Double>) {
    narrate("\n$heroName intuitively knows how much money each patron has:")
    patronGold.forEach { (patron, balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}

// пример приведения Either
fun parse(s: String): Either<NumberFormatException, Int> =
    if (s.matches(Regex("-?[0-9]+"))) {
        Either.Right(s.toInt())
    } else {
        Either.Left(NumberFormatException("$s is not a valid integer."))
    }
