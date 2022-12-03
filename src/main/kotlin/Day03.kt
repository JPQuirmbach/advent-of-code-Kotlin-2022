fun main() {

    fun findCommonLetter(s1: String, s2: String): Char {
        s1.forEach {
            if (s2.contains(it)) {
                return it
            }
        }
        error("Couldn't find common letter")
    }

    fun findCommonLetter2(s: List<String>): Char {
        s.component1()
            .forEach {
                if (s.component2().contains(it) && s.component3().contains(it)) {
                    return it
                }
            }
        error("Couldn't find common letter")
    }

    fun countPriority(c: Char): Int {
        if (c.isLowerCase()) {
            return c - 'a' + 1
        }
        return c - 'A' + 27
    }

    fun part1(input: String): Int {
        return input.lines()
            .map {
                val chunked = it.chunked(it.length / 2)
                chunked.first() to chunked.last()
            }
            .map { findCommonLetter(it.first, it.second) }
            .sumOf { countPriority(it) }
    }

    fun part2(input: String): Int {
        return input.lines()
            .chunked(3)
            .map { findCommonLetter2(it) }
            .sumOf { countPriority(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
