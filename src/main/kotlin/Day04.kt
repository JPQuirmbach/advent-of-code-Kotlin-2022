fun main() {

    fun getRange(s: String): Set<Int> {
        val split = s.split("-")
        return IntRange(split.first().toInt(), split.last().toInt()).toSet()
    }

    fun part1(input: String): Int {
        return input.lines()
            .map {
                val split = it.split(",")
                split.first() to split.last()
            }.sumOf {
                val first = getRange(it.first)
                val second = getRange(it.second)
                if (first.containsAll(second) || second.containsAll(first))
                    1L
                else
                    0L
            }.toInt()
    }

    fun part2(input: String): Int {
        return input.lines()
            .map {
                val split = it.split(",")
                split.first() to split.last()
            }.sumOf {
                val first = getRange(it.first)
                val second = getRange(it.second)
                if ((first intersect second).isNotEmpty())
                    1L
                else
                    0L
            }.toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
