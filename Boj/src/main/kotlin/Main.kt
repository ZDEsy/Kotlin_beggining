import java.util.logging.Handler
import kotlin.math.round

fun main(args: Array<String>) {
    val dice = Dice(10)
    val fighter = Fighter("David Buchtela", 20, 8, 5, dice)
    val enemy = Fighter("Spongebob", 27, 8, 5, dice)
    println("Bojovník jednaaaa  jeeeee: $fighter!!!!")
    println("Život: ${fighter.healthBar()}")
    Thread.sleep(1000)
    println("A na druhé straně... neskrotný.... neodolatelný.... ŽLUTÝ.... $enemy!!!!!!")
    println("Život: ${enemy.healthBar()}\n\n")

    while(fighter.isAlive() && enemy.isAlive())
    {
        fighter.doAttack(enemy)
        println(fighter.lastNotify())
        Thread.sleep(1000)
        println(enemy.lastNotify() + "\n" + enemy.healthBar() + "\n")
        if(!enemy.isAlive()){
            break
        }
        enemy.doAttack(fighter)
        Thread.sleep(1000)
        println(enemy.lastNotify())
        Thread.sleep(1000)
        println(fighter.lastNotify() + "\n" + fighter.healthBar() + "\n")
        Thread.sleep(1000)
    }

}

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

class Fighter(private val name: String, private var health: Int, private val attack: Int,
              private val defense: Int, private val dice: Dice)
{
    private var notify = ""
    private val maxHealth: Int = health

    override fun toString(): String {
        return name
    }

    private fun setNotify(notify: String)
    {
        this.notify = notify
    }

    fun lastNotify(): String
    {
        return notify
    }

    fun isAlive(): Boolean
    {
        return health > 0
    }

    fun healthBar(): String
    {
        var s = "["
        val total = 20
        var calcul = round((health.toDouble()/maxHealth) * total).toInt()
        if((calcul == 0) && (isAlive()))
            calcul = 1
        s = s.padEnd(calcul + s.length, '#')
        s = s.padEnd(total - calcul + s.length, ' ')
        s += "]"
        return s
    }

    fun defend(hit: Int)
    {
        val hurt = hit - (defense + dice.diceThrow())
        if(hurt > 0)
        {
            health -= hurt
            notify = "$name dostal poskozeni $hurt"
            if (health < 0)
            {
                health = 0
                notify += " a tím pádem umřel X-X"
            }
        }
        else notify = "$name Vyhnul se B)"
        setNotify(notify)
    }

    fun doAttack(enemy: Fighter)
    {
        val hit = attack + dice.diceThrow()
        setNotify("$name útočí s útokem $hit")
        enemy.defend(hit)
    }
}