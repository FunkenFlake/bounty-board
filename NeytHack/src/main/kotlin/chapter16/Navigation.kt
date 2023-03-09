package chapter16

data class Coordinate(val x: Int, val y: Int) {
    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}

enum class Direction(
    private val directionCoordinate: Coordinate
) {
    North(Coordinate(0, -1)),
    East(Coordinate(1, 0)),
    South(Coordinate(0, 1)),
    West(Coordinate(-1, 0)); // тут отдел€юща€ перечеслени€ от ф-ии точка с зап€той

    fun updateCoordinate(coordinate: Coordinate) =
        coordinate + directionCoordinate
}

data class Grab(val grab: String)

data class Rotation(val rotation: Int)

enum class DirectionRotation(
    private val grab: Grab,
    private val rotation: Rotation
) {
    BS(Grab("Melon"), Rotation(360)),
    FS(Grab("Indy"), Rotation(180))
}
