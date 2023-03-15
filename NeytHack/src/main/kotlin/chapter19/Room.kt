package chapter19

open class Room(
    val name: String
) {
    protected open val status = "Calm" // protected уровень видимости кслассом и его подклассами
    open val lootBox: LootBox<Loot> = LootBox.random()
    open fun description() = "$name (Currently: $status)"
    open fun enterRoom() {
        narrate("There is nothing to do here")
    }
}

class TownSquare : Room(        // вызов конструктора справа от :
    name = "The Town Square"    // я решил обозначить аргумент, просто чтобы было понятней
) {
    override val status = "Bustling"
    private var bellSound = "GWONG"
    val hatDropOffBox = DropOffBox<Hat>()
    val gemDropOffBox = DropOffBox<Gemstones>()
    final override fun enterRoom() {    // final - последнее переопределение,
                                        // следующий подклас уже не сможет переопределить enterRoom()
        narrate("The villagers rally and cheer as the hero enters")
        ringBell()
    }

    fun ringBell() {
        narrate("The bell tower announces the hero's presence: $bellSound")
    }

    fun <T> sellLoot(
        loot: T
    ) : Int where T : Loot, T : Sellable {
        return when (loot) {
            is Hat -> hatDropOffBox.sellLoot(loot)
            is Gemstones -> gemDropOffBox.sellLoot(loot)
            else -> 0
        }
    }
}

open class MonsterRoom(
    name: String,
    var monster: Monster? = Goblin()
) : Room(name) {
    override fun description() =
        super.description() + " (Creature: ${monster?.description ?: "None"})"
    override fun enterRoom() {
        if (monster == null) {
            super.enterRoom()
        } else {
            narrate("Danger is lurking in this room")
        }
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