fun main() {
    fun parseInput(input: String) = input.split("\n\n")
        .map { elf -> elf.lines().map { it.toInt() } }

    fun part1(input: String): Int {
        return parseInput(input)
            .maxOf { it.sum() }
    }

    fun part2(input: String): Int {
        return parseInput(input)
            .map { it.sum() }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
