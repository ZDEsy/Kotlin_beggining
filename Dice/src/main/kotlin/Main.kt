class Dice(setWallsNum: Int) {
    val wallsNum: Int

    init {
        wallsNum = setWallsNum
    }
    constructor(): this(6)

    fun diceThrow(): Int {
        return (1..wallsNum).shuffled().first()
    }

    override fun toString(): String {
        return "Kostka s $wallsNum stěnami"
    }
}

fun main(args: Array<String>) {
    val dice = Dice(10)
    println(dice.wallsNum)
    println(dice)

    for(i in 0..9){
        print(dice.diceThrow().toString() + " ")
    }
}