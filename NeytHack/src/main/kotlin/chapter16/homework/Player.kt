package chapter16.homework

class Player( // ��� � ( � ����������� ) � ���� �����������
    initialName: String,
    val hometown: String = "Neversummer",
    var healthPoints: Int,
    var isImmortal: Boolean,
) {
    var name = initialName
        get() = field.replaceFirstChar { it.uppercase() }
        private set(value) {
            field = value.trim() // ������� �������
        }

    val someSome = 99 // ��� ��������, ��� ���������� �� ������ ������. ��. ������ someSome

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter() } -> "The Witness Protection Member"
            name.count {it.lowercase() in "aeiouyj" } > 4 -> "The Master of Vowel"
            else -> "The Renowned Hero"
        }

    val prophecy by lazy {
        narrate("$name embarks on an arduous quest to locate a fortune teller")
//        Thread.sleep(3000)
        narrate("The fortune teller bestows a prophecy upon $name")
        "An intrepid hero from $hometown shall some day "+ listOf(
            "form an unlikely bond between two warring factions",
            "take possession of an otherworldly blade",
            "bring the gift of creation back to the world",
            "best the world-eater"
        ).random()
    }

    init { // ��� ����� ����� ��������� �������� ���������, ��������� ��������� ���.
        require(healthPoints > 0) {"healthPoints must be greater than zero"}
        require(name.isNotBlank()) {"Player must have a name"}
    }

    constructor(name: String) : this(
        initialName = name,
        healthPoints = 100,
        isImmortal = false,
    ) { // ������ ������ ���. ������������ ����� � ������ ��������, ������ �� � ���?))
        // �� ����� ���������� �����-�� ����������� �� �������� ����� ������
        if (name.equals("Jason", ignoreCase = true)) { //equals, ������ ==, ignoreCase - �����!
            healthPoints = 500
        }
    }

    constructor(name: String, healthPoints: Int) : this(
        // ��� �� �������������� ���. �����������, ���������� �� ��������� ��� �������� ����������
        // player = Player( �� � ��� ��� �� �������������� �����������)
        // this - ������� �������� �����������.
        initialName = name, // �������� ����� ������������ ��������
        "Jacksonville",
        healthPoints, // � ����� � ��� ����, ������ ����� ����������
        false,
    ) {
        if (healthPoints == 1) isImmortal = true
        val someSome = 100 // � ��� ��� ��� ����������

    }

    fun castFireball(numFireballs: Int = 2) {
        narrate("A glass of Fireball springs into existence (x$numFireballs)")
    }

    fun castFrostball() {
        narrate("A glass is Ice, from Frostball")
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