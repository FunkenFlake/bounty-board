

fun narrate(message: String) {
    val narrationModifier: (String) -> String = { message ->
        val numExclamationPoints = 3
        message.uppercase() + "!".repeat(numExclamationPoints)
    }
    println(narrationModifier(message))
}