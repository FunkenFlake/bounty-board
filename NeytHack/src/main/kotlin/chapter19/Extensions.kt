package chapter19

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

val String.numVowels
    get() = count { it.lowercase() in "aeiou"}


// ���� ������������ ����� ��� (Any) ������ ����������� <T>, �� ����������, ��� �-�� ����������
// ��� Any, � �� ��� ������� ��������� �� ����� (�������� String), � �������������� <T>
// ��� �������� �������� � �-�� ���������� ��� ���, ������� ��� ��������.
fun <T> T.print(): T {
    println(this)
    return this
}

operator fun List<List<Room>>.get(coordinate: Coordinate) =
    getOrNull(coordinate.y)?.getOrNull(coordinate.x)

infix fun Coordinate.move(direction: Direction) =
    direction.updateCoordinate(this)