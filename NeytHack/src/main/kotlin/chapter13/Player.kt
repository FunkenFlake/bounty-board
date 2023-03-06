package chapter13

class Player( // вот в ( я конструктор ) и есть конструктор
    initialName: String,
    val hometown: String = "Neversummer",
    var healthPoints: Int,
    var isImmortal: Boolean,
) {
    var name = initialName
        get() = field.replaceFirstChar { it.uppercase() }
        private set(value) {
            field = value.trim() // убирает пробелы
        }

    val someSome = 99 // это свойство, оно определено на уровне класса. см. другую someSome

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter() } -> "The Witness Protection Member"
            name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
            else -> "The Renowned Hero"
        }

    val prophecy by lazy {
        narrate("$name embarks on an arduous quest to locate a fortune teller")
        Thread.sleep(3000)
        narrate("The fortune teller bestows a prophecy upon $name")
        "An intrepid hero from $hometown shall some day "+ listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }

    init { // тут можно также присвоить значения свойствам, вычислить состояния итп.
        require(healthPoints > 0) {"healthPoints must be greater than zero"}
        require(name.isNotBlank()) {"Player must have a name"}
    }

    constructor(name: String) : this(
        initialName = name,
        healthPoints = 100,
        isImmortal = false,
    ) { // помимо самого доп. конструктора можно и логики добавить, почему бы и нет?))
        // по факту получается какое-то ответвление от основной ветки класса
        if (name.equals("Jason", ignoreCase = true)) { //equals, аналог ==, ignoreCase - браво!
            healthPoints = 500
        }
    }

    constructor(name: String, healthPoints: Int) : this(
        // тут мы подготавливаем доп. конструктор, аналогично мы поступаем при создании экземпляра
        // player = Player( ну и тут так же подготавливаем конструктор)
        // this - вызовет основной конструктор.
        initialName = name, // передаем через именнованный параметр
        "Jacksonville",
        healthPoints, // а можно и без него, только легко запутаться
        false,
    ) {
        if (healthPoints == 1) isImmortal = true
        val someSome = 100 // а вот это уже переменная

    }

    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }

    fun changeName(newName: String) {
        narrate("$name legally changes their name to $newName")
        name = newName
    }

    fun prophesize() {
        narrate("$name thinks about their future")
        narrate("A fortune teller told Madrigal, \"$prophecy\"")
    }

    fun weaponDamage(attackPower: Int, skill: Int, bonusDamage: Int): Int {
        return attackPower * skill * (1 + bonusDamage)
    }
}