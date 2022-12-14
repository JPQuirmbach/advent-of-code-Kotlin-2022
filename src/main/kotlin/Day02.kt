fun main() {

    fun parseInput(input: String) = input.split("\n")
        .flatMap { line ->
            line.split(" ")
                .zipWithNext()
        }

    fun part1(input: String): Int {
        return parseInput(input)
            .map { Pair(toSymbol(it.first), toSymbol(it.second)) }
            .sumOf {
                val shapeScore = it.second.points
                val outcome = calcOutcome(it.second, it.first)
                shapeScore + outcome.points
            }
    }

    fun part2(input: String): Int {
        return parseInput(input)
            .map { Pair(toSymbol(it.first), it.second) }
            .sumOf {
                val move = determineMove(it.second)
                val shapeScore = when (move) {
                    Result.LOSE -> loseMove(it.first).points
                    Result.DRAW -> drawMove(it.first).points
                    Result.WIN -> winMove(it.first).points
                }
                shapeScore + move.points
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun calcOutcome(player: Symbol, opponent: Symbol): Result {
    if (player == opponent) {
        return Result.DRAW
    }
    if (player == Symbol.ROCK && opponent == Symbol.SCISSORS
        || player == Symbol.SCISSORS && opponent == Symbol.PAPER
        || player == Symbol.PAPER && opponent == Symbol.ROCK
    ) {
        return Result.WIN
    }
    return Result.LOSE
}

fun toSymbol(input: String) = when (input) {
    "A", "X" -> Symbol.ROCK
    "B", "Y" -> Symbol.PAPER
    "C", "Z" -> Symbol.SCISSORS
    else -> throw Exception("Invalid")
}

fun winMove(opponent: Symbol) = when (opponent) {
    Symbol.ROCK -> Symbol.PAPER
    Symbol.PAPER -> Symbol.SCISSORS
    Symbol.SCISSORS -> Symbol.ROCK
}

fun drawMove(opponent: Symbol) = opponent

fun loseMove(opponent: Symbol) = when (opponent) {
    Symbol.ROCK -> Symbol.SCISSORS
    Symbol.PAPER -> Symbol.ROCK
    Symbol.SCISSORS -> Symbol.PAPER
}

fun determineMove(player: String) = when (player) {
    "X" -> Result.LOSE
    "Y" -> Result.DRAW
    "Z" -> Result.WIN
    else -> throw Exception("Invalid")
}

enum class Symbol(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
}

enum class Result(val points: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0)
}
