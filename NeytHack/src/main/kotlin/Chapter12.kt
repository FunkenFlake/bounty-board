import java.io.File

// ������� ������� ���������, �� ��������

fun main() {

    val number: Int? = 10

    // apply
    // ��� apply �������� ������ ���
    val patrons: MutableList<String> = mutableListOf()
    if (isAfterMidnight) { patrons.add("Sidney") }
    if (isOpenMicNight) { patrons.add("Janet") }
    if (isHappyHour) { patrons.add("Jamie") }
    if (patrons.contains("Janet") || patrons.contains("Jamie")) { patrons.add("Hal") }

    val guestList: List<String> = patrons.toList()

    // ��������� apply (��������� ������� ��������� �-�� ��� �������-����������)
    val guestList: List<String> = mutableListOf<String>().apply {
        if (isAfterMidnight) { patrons.add("Sidney") }
        if (isOpenMicNight) { patrons.add("Janet") }
        if (isHappyHour) { patrons.add("Jamie") }
        if (contains("Janet") || contains("Jamie")) { add("Hal") }
    }.toList()

    // let
    val patrons: List<String> = listOf("Sidney", "Janet", "Jamie")
    val greeting = patrons.firstOrNull()?.let {
        "$it walks over to Madrigal and says, \"Hi! I'm $it. Welcome to Kronstadt!\""
    } ?: "Nobody greets Madrigal because the tavern is empty"

    // run
    // ������������ ������� ���������, ���������� ��������� ������-���������
    val tavernPlayList = mutableListOf("Korobeiniki", "Kalinka", "Katusha")
    val nowPlayingMessage: String = tavernPlayList.run {
        shuffle()
        "${first()} is currently playing in the tavern"
    }

    // with
    // ������� �������� ��������� � ������ ��������� (receiver - ����������)
    // � ��������� �-�� ���������� run
    val nameTooLong = with("Polarcubis, Supreme Master of NyetHack") {
        length >= 20
    }

    // also
    // ���� ��� � let, ������ ���������� �� ���������, � ��� ����������
    // �.�. ���� ��������
    // numOne = 1
    // numOne = numOne.also { it + 1 } ��� ������ it, �.�. 1, � let ������ 2, �.�. ��������� ��������� ( � ������ ������ �����)
    var fileContents: List<String>
    File("file.txt")
        .also { print(it.name) }
        .readLines()
        .also { fileContents = it }

    // takeIf
    // ��������� ������� ��� �������� � ���������� ��� ���������� ���� ������, null - ���� ����
    // � ������� ���� ��������, ������ ���� ����������
    val fileContents = File("myfile.txt")
        .takeIf { it.exists() } // exists() ��������� ������������� �����
        ?.readText()            // ������ ����, ���� ���������� null

    // ��� takeIf
    val file = File("myfile.txt")
    val fileContents = if (file.exists()) {
        file.readText()
    } else {
        null
    }

    // takeUnless
    // ���� �����, ������ ���������� �������� ��������, ���� ����
    // ��� �� ��� (�� ������������� � REPL), takeUnless (���� ��) ������ ���� �����, ������ ��������
    // ������ ����, ���� �� �� �������� �������, � ���� ������ - ���������� null
    val fileContents = File("myfile.txt").takeUnless { it.isHidden }?.readText()

}

