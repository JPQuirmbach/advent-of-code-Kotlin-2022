import java.util.*
import kotlin.collections.ArrayDeque

fun main() {

    fun createStack(input: String): List<ArrayDeque<Char>> {
        val stack = List(9) { ArrayDeque<Char>() }
        val crates = input.lines()
            .dropLast(1)
            .reversed()
            .map { it ->
                it.chunked(4) {
                    if (it[1].isLetter()) it[1] else null
                }
            }
        for (row in crates) {
            for ((idx, char) in row.withIndex()) {
                if (char != null) stack[idx].addLast(char)
            }
        }
        return stack
    }

    fun part1(input: String): String {
        val (ship, moves) = input.split("\n\n")
        val stack = createStack(ship)

        moves.lines()
            .map {
                it.split(" ")
                    .filter { Objects.nonNull(it.toIntOrNull()) }
                    .map { it.toInt() }
            }
            .forEach {
                val (amount, from, to) = it
                val fromStack = stack[from - 1]
                val toStack = stack[to - 1]

                repeat(amount) {
                    toStack.addLast(fromStack.removeLast())
                }
            }
        return stack.filter { it.isNotEmpty() }
            .joinToString("") { "${it.last()}" }
    }


    fun part2(input: String): String {
        val (ship, moves) = input.split("\n\n")
        val stack = createStack(ship)

        moves.lines()
            .map {
                it.split(" ")
                    .filter { Objects.nonNull(it.toIntOrNull()) }
                    .map { it.toInt() }
            }
            .forEach {
                val (amount, from, to) = it
                val fromStack = stack[from - 1]
                val toStack = stack[to - 1]

                val inClaw = fromStack.takeLast(amount)
                repeat(amount) { fromStack.removeLast() }

                for (crate in inClaw) {
                    toStack.addLast(crate)
                }

            }
        return stack.filter { it.isNotEmpty() }
            .joinToString("") { "${it.last()}" }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
