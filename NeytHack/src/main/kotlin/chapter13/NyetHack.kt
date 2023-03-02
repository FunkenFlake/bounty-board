// ������� � ����� 13 ������, ���� ������ ��� ������ ����� ����������

package chapter13

// ����������� ������ ������� ��������������� ���, �������
// ���������� player ����� ��� Player
val player = Player()

fun main() {
    narrate("${player.name} is ${player.title}")
    player.changeName("Aurelia")
    narrate("${player.name}, ${player.title}, heads to the town square")

    visitTavern()
    player.castFireball()
}

private fun promptHeroName(): String {
    // ��� { message... ��� ������ ��������, �������� ��������� �������, �����������
    // ����������� ���������, �� �������� ������� ������ ��� 2��� ���������, ������� ������ ��� �������
    // ������� �� ������� ������� � ����� ������� �������� ��� ���������� ���������
    narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
        // ������� ��������� ������ ������
        "\u001b[33;1m$message\u001b[0m"
    }

    println("Madrigal")
    return "Madrigal"
}