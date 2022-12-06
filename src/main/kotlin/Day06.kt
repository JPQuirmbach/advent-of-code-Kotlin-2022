fun main() {

    fun parseInput(input: String, markerSize: Int): Int {
        val filter = input.windowed(markerSize)
            .first { it ->
                it.groupingBy { it }
                    .eachCount()
                    .values
                    .max() == 1
            }
        return input.indexOf(filter) + markerSize
    }


    fun part1(input: String): Int {
        return parseInput(input, 4)
    }

    fun part2(input: String): Int {
        return parseInput(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
