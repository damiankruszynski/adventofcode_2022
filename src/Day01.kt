fun main() {
    fun getMapOfElf(list: List<String>): MutableMap<String, Int> {
        val mapOfElf = mutableMapOf<String, Int>()
        var elfCounter = 1
        list.forEachIndexed { index, it ->
            val elfName = "Elf_$elfCounter"
            if (it.isBlank()) {
                elfCounter++
            } else if (list.lastIndex == index) {
                mapOfElf[elfName] = mapOfElf.getOrDefault(elfName, 0) + it.toInt()
            } else {
                mapOfElf[elfName] = mapOfElf.getOrDefault(elfName, 0) + it.toInt()
            }
        }
        return mapOfElf
    }

    fun part1(input: List<String>): Int {
        val mapOfElf = getMapOfElf(input)
        return mapOfElf.values.maxOf { it }
    }

    fun part2(input: List<String>): Int {
        val mapOfElf = getMapOfElf(input)
        return mapOfElf.values.sortedDescending().take(3).sum()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    part1(input).println()


    println(part2(testInput))
    check(part2(testInput) == 45000)

    part2(input).println()
}
