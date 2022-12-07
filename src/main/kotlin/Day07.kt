
fun main() {

    fun parseInput(input: List<String>): Dir {
        val root = Dir("/")
        var current = root

        input.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> current = current.parent!!
                line.startsWith("$ cd") -> current = current.dirs.first { it.name == line.substringAfter("cd ") }
                line.startsWith("dir") -> current.dirs.add(Dir(line.substringAfter("dir "), current))
                !line.contains("$") -> current.files.add(File(line.substringBefore(" ").toInt()))
            }
        }
        return root
    }

    fun part1(input: String): Int {
        return parseInput(input.lines())
            .allDirs()
            .map { it.size() }
            .filter { it < 100_000 }
            .sum()
    }

    fun part2(input: String): Int {
        val root = parseInput(input.lines())
        val fileSystemSize = 70_000_000
        val updateSize = 30_000_000
        val spaceToFree = updateSize - (fileSystemSize - root.size())

        return root.allDirs()
            .map { it.size() }
            .sorted()
            .first { it >= spaceToFree }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

data class File(val size: Int)

data class Dir(
    val name: String,
    val parent: Dir? = null,
    val dirs: MutableList<Dir> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
) {
    fun allDirs(): List<Dir> = dirs + dirs.flatMap { it.allDirs() }
    fun size(): Int = files.sumOf { it.size } + dirs.sumOf { it.size() }
}


