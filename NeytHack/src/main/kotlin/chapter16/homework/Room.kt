package chapter16.homework

open class Room(
    val name: String
) {
    protected open val status = "Calm" // protected ������� ��������� �������� � ��� �����������
    fun description() = "$name (Currently: $status)"
    open fun enterRoom() {
        narrate("There is nothing to do here")
    }
}

class TownSquare : Room(        // ����� ������������ ������ �� :
    name = "The Town Square"    // � ����� ���������� ��������, ������ ����� ���� ��������
) {
    override val status = "Bustling"
    private var bellSound = "GWONG"
    final override fun enterRoom() {    // final - ��������� ���������������,
                                        // ��������� ������� ��� �� ������ �������������� enterRoom()
        narrate("The villagers rally and cheer as the hero enters")
        ringBell()
    }

    fun ringBell() {
        narrate("The bell tower announces the hero's presence: $bellSound")
    }
}

// for example for me
class Kitchen : Room(
    name = "The kitchen room"
) {
    override val status = "Here is the cooking!"
    private val blade = "shsss"

    override fun enterRoom() {
        narrate("Cooking battle, knife blade flew over the ear $blade")

    }
}