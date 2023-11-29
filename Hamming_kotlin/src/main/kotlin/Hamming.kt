object Hamming {
    fun compute(leftStrand: String, rightStrand: String): String {
        var difference: Int = 0
        if(leftStrand.length == rightStrand.length)
        {
            for (i in leftStrand.indices)
            {
                if (leftStrand[i] != rightStrand[i])
                {
                    difference++
                }
            }
            return difference.toString()
        }
        else return "left and right strands must be of equal length"
    }



}

fun main() {

    println(Hamming.compute("Honza","Honza"))
}