fun main() {

    fun priority(group: List<String>): Int {
        val chars = group.flatMap { it.toSet() }
        val sharedItem = chars.groupingBy { it }
            .eachCount()
            .maxBy { it.value }.key
        return if (sharedItem.isLowerCase())
            sharedItem - 'a' + 1
        else
            sharedItem - 'A' + 27
    }

    fun part1(input: String): Int {
        return input.lines()
            .map { it.chunked(it.length / 2) }
            .sumOf { priority(it) }
    }

    fun part2(input: String): Int {
        return input.lines()
            .chunked(3)
            .sumOf { priority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
