import java.io.File

// ‘ункции области видимости, их описание

fun main() {

    val number: Int? = 10

    // apply
    // без apply придетс€ писать так
    val patrons: MutableList<String> = mutableListOf()
    if (isAfterMidnight) { patrons.add("Sidney") }
    if (isOpenMicNight) { patrons.add("Janet") }
    if (isHappyHour) { patrons.add("Jamie") }
    if (patrons.contains("Janet") || patrons.contains("Jamie")) { patrons.add("Hal") }

    val guestList: List<String> = patrons.toList()

    // использу€ apply (позвол€ет вызвать несколько ф-ий дл€ объекта-получател€)
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
    // ограничивает область видимости, возвращает результат л€мбда-выражени€
    val tavernPlayList = mutableListOf("Korobeiniki", "Kalinka", "Katusha")
    val nowPlayingMessage: String = tavernPlayList.run {
        shuffle()
        "${first()} is currently playing in the tavern"
    }

    // with
    // требует передачу аргумента в первом параметре (receiver - получатель)
    // в остальном ф-и€ аналогична run
    val nameTooLong = with("Polarcubis, Supreme Master of NyetHack") {
        length >= 20
    }

    // also
    // тоже что и let, только возвращает не выражение, а им€ получател€
    // т.е. если написать
    // numOne = 1
    // numOne = numOne.also { it + 1 } оно вернет it, т.е. 1, а let вернет 2, т.е. результат выражени€ ( в данном случае сумму)
    var fileContents: List<String>
    File("file.txt")
        .also { print(it.name) }
        .readLines()
        .also { fileContents = it }

    // takeIf
    // вычисл€ет условие или предикат и возвращает им€ получател€ если истина, null - если ложь
    // в примере файл читаетс€, только если существует
    val fileContents = File("myfile.txt")
        .takeIf { it.exists() } // exists() провер€ет существование файла
        ?.readText()            // читает файл, либо возвращает null

    // без takeIf
    val file = File("myfile.txt")
    val fileContents = if (file.exists()) {
        file.readText()
    } else {
        null
    }

    // takeUnless
    // тоже самое, только возвращает исходное значение, если ложь
    // как по мне (из эксперементов в REPL), takeUnless (пока не) делает тоже самое, только наоборот
    // читает файл, если он не €вл€етс€ скрытым, в ином случае - возвращает null
    val fileContents = File("myfile.txt").takeUnless { it.isHidden }?.readText()

}

