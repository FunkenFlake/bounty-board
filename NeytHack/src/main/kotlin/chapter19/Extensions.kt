package chapter19

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

val String.numVowels
    get() = count { it.lowercase() in "aeiou"}


// Если использовать любой тип (Any) вместо обобщенного <T>, то получается, что ф-ия возвращает
// тип Any, а не тот который подавался на входе (например String), с использованием <T>
// эта проблема решается и ф-ия возвращает тот тип, который она получала.
fun <T> T.print(): T {
    println(this)
    return this
}

operator fun List<List<Room>>.get(coordinate: Coordinate) =
    getOrNull(coordinate.y)?.getOrNull(coordinate.x)

infix fun Coordinate.move(direction: Direction) =
    direction.updateCoordinate(this)